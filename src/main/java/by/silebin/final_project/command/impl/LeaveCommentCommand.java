package by.silebin.final_project.command.impl;

import by.silebin.final_project.command.*;
import by.silebin.final_project.entity.Comment;
import by.silebin.final_project.entity.User;
import by.silebin.final_project.exception.ServiceException;
import by.silebin.final_project.service.CommentService;
import by.silebin.final_project.service.impl.CommentServiceImpl;
import by.silebin.final_project.validator.ParamValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class LeaveCommentCommand implements Command {

    private static final Logger logger = LogManager.getLogger(LeaveCommentCommand.class);

    private final CommentService commentService = CommentServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        Comment comment = new Comment();

        User user = (User) request.getSession().getAttribute(RequestAttribute.USER);
        if(user == null) {
            request.setAttribute(RequestAttribute.MESSAGE, "you should log in to leave comment");
            return new Router(PagePath.LOGIN_PAGE, Router.RouterType.FORWARD);
        }
        String cocktailIdParam = request.getParameter(RequestParameter.COCKTAIL_ID);
        String markParam = request.getParameter(RequestParameter.RATING);
        if(!ParamValidator.validateIntParam(cocktailIdParam) || !ParamValidator.validateIntParam(markParam)) {
            return new Router(PagePath.NOT_FOUND_PAGE, Router.RouterType.REDIRECT);
        }
        int cocktailId = Integer.parseInt(cocktailIdParam);
        comment.setCocktailId(cocktailId);
        comment.setUserId(user.getUserId());
        String text = request.getParameter(RequestParameter.COMMENT);
        comment.setText(text);
        int mark = Integer.parseInt(markParam);
        comment.setMark(mark);
        try {
            commentService.leaveComment(comment);
        } catch (ServiceException e) {
            logger.error(e);
            request.getSession().setAttribute(RequestAttribute.EXCEPTION, e);
            return new Router(PagePath.ERROR_PAGE, Router.RouterType.REDIRECT);
        }
        return new Router(PagePath.GO_TO_COCKTAIL_PAGE + cocktailId, Router.RouterType.REDIRECT);
    }
}
