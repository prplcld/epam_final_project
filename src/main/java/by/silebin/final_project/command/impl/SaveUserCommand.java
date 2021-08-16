package by.silebin.final_project.command.impl;

import by.silebin.final_project.command.*;
import by.silebin.final_project.entity.User;
import by.silebin.final_project.exception.ServiceException;
import by.silebin.final_project.service.UserService;
import by.silebin.final_project.service.impl.UserServiceImpl;
import by.silebin.final_project.validator.ParamValidator;
import by.silebin.final_project.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SaveUserCommand implements Command {

    private final UserService userService = UserServiceImpl.getInstance();
    private static final Logger logger = LogManager.getLogger(SaveUserCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(RequestAttribute.USER);
        String idParam = request.getParameter(RequestParameter.ID);
        String username = request.getParameter(RequestParameter.USERNAME);
        String password = request.getParameter(RequestParameter.PASSWORD);
        String email = request.getParameter(RequestParameter.EMAIL);
        String oldLogin = request.getParameter(RequestParameter.OLD_LOGIN);

        if (!ParamValidator.validateIntParam(idParam)) {
            request.getSession().setAttribute(RequestAttribute.MESSAGE, "user not found");
            return new Router(PagePath.NOT_FOUND_PAGE, Router.RouterType.REDIRECT);
        }

        if (user == null) {
            request.getSession().setAttribute(RequestAttribute.MESSAGE, "you should login to edit user");
            return new Router(PagePath.GO_TO_LOGIN, Router.RouterType.REDIRECT);
        }

        int id = Integer.parseInt(idParam);

        if(!UserValidator.validateLogin(username) || !UserValidator.validateEmail(email)) {
            request.getSession().setAttribute(RequestAttribute.MESSAGE, "username or email are not valid");
            return new Router(PagePath.GO_TO_EDIT_USER + id, Router.RouterType.REDIRECT);
        }

        User updatedUser = new User();
        updatedUser.setUserId(id);
        updatedUser.setLogin(username);
        updatedUser.setEmail(email);

        try {
            userService.update(updatedUser, password, oldLogin);
        } catch (ServiceException e) {
            logger.error(e);
            return new Router(PagePath.ERROR_PAGE, Router.RouterType.REDIRECT);
        }

        return new Router(PagePath.GO_TO_PROFILE + id, Router.RouterType.REDIRECT);
    }
}
