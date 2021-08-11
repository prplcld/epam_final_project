package by.silebin.final_project.command.impl;

import by.silebin.final_project.command.*;
import by.silebin.final_project.entity.Role;
import by.silebin.final_project.entity.User;
import by.silebin.final_project.exception.ServiceException;
import by.silebin.final_project.service.CommentService;
import by.silebin.final_project.service.impl.CommentServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DeleteCommentCommand implements Command {

    CommentService commentService = CommentServiceImpl.getInstance();
    private static final Logger logger = LogManager.getLogger(DeleteCommentCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter(RequestParameter.ID));
        int cocktailId = Integer.parseInt(request.getParameter(RequestParameter.COCKTAIL_ID));
        int userId = Integer.parseInt(request.getParameter(RequestParameter.USER_ID));
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(RequestAttribute.USER);

        if (user == null) {
            return new Router(PagePath.LOGIN_PAGE, Router.RouterType.REDIRECT);
        }

        if (user.getUserId() != userId || user.getRole() != Role.ADMIN) {
            return new Router(PagePath.NOT_FOUND_PAGE, Router.RouterType.REDIRECT);
        }

        try {
            commentService.delete(id);
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            return new Router(PagePath.ERROR_PAGE, Router.RouterType.FORWARD);
        }
        return new Router(PagePath.GO_TO_COCKTAIL_PAGE + cocktailId, Router.RouterType.FORWARD);
    }
}