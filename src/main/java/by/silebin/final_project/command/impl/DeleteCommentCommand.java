package by.silebin.final_project.command.impl;

import by.silebin.final_project.command.*;
import by.silebin.final_project.entity.Role;
import by.silebin.final_project.entity.User;
import by.silebin.final_project.exception.ServiceException;
import by.silebin.final_project.service.CommentService;
import by.silebin.final_project.service.impl.CommentServiceImpl;
import by.silebin.final_project.validator.ParamValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DeleteCommentCommand implements Command {

    private final CommentService commentService = CommentServiceImpl.getInstance();
    private static final Logger logger = LogManager.getLogger(DeleteCommentCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {

        String idParam = request.getParameter(RequestParameter.ID);
        String cocktailIdParam = request.getParameter(RequestParameter.COCKTAIL_ID);
        String userIdParam = request.getParameter(RequestParameter.USER_ID);
        if (!ParamValidator.validateIntParam(idParam) || !ParamValidator.validateIntParam(cocktailIdParam) || !ParamValidator.validateIntParam(userIdParam)) {
            return new Router(PagePath.NOT_FOUND_PAGE, Router.RouterType.REDIRECT);
        }

        int id = Integer.parseInt(idParam);
        int cocktailId = Integer.parseInt(cocktailIdParam);
        int userId = Integer.parseInt(userIdParam);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(RequestAttribute.USER);


        if (user == null || (user.getRole() != Role.ADMIN && userId != user.getUserId())) {
            request.setAttribute(RequestAttribute.MESSAGE, "you should login as admin or creator of this comment");
            return new Router(PagePath.LOGIN_PAGE, Router.RouterType.FORWARD);
        }

        try {
            commentService.delete(id);
        } catch (ServiceException e) {
            logger.error(e);
            request.getSession().setAttribute(RequestAttribute.EXCEPTION, e);
            return new Router(PagePath.ERROR_PAGE, Router.RouterType.REDIRECT);
        }
        return new Router(PagePath.GO_TO_COCKTAIL_PAGE + cocktailId, Router.RouterType.FORWARD);
    }
}
