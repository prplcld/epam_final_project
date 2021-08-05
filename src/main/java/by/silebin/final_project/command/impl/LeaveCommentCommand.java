package by.silebin.final_project.command.impl;

import by.silebin.final_project.command.Command;
import by.silebin.final_project.command.PagePath;
import by.silebin.final_project.command.Router;
import by.silebin.final_project.entity.Comment;
import by.silebin.final_project.entity.User;
import by.silebin.final_project.exception.ServiceException;
import by.silebin.final_project.service.CommentService;
import by.silebin.final_project.service.impl.CommentServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class LeaveCommentCommand implements Command {

    private static final Logger logger = LogManager.getLogger(LeaveCommentCommand.class);

    CommentService commentService = new CommentServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        Comment comment = new Comment();

        User user = (User) request.getSession().getAttribute("user");
        if(user == null) {
            return new Router(PagePath.LOGIN_PAGE, Router.RouterType.FORWARD);
        }
        int cocktailId = (int) request.getSession().getAttribute("cocktailId");
        comment.setCocktailId(cocktailId);
        comment.setUserId(user.getUserId());
        String text = request.getParameter("comment");
        comment.setText(text);
        int mark = Integer.parseInt(request.getParameter("rating"));
        comment.setMark(mark);
        try {
            commentService.leaveComment(comment);
        } catch (ServiceException e) {
            logger.error(e);
            return new Router(PagePath.ERROR_PAGE, Router.RouterType.FORWARD);
            //FIXME
        }
        return new Router(PagePath.GO_TO_COCKTAIL_PAGE + cocktailId, Router.RouterType.FORWARD);
    }
}
