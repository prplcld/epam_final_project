package by.silebin.final_project.command.impl;

import by.silebin.final_project.command.*;
import by.silebin.final_project.entity.User;
import by.silebin.final_project.exception.ServiceException;
import by.silebin.final_project.service.UserService;
import by.silebin.final_project.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginUserCommand implements Command {

    private static final Logger logger = LogManager.getLogger(LoginUserCommand.class);

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {

        String login = request.getParameter(RequestParameter.USERNAME);
        String password = request.getParameter(RequestParameter.PASSWORD);

        Optional<User> userOptional;

        try {
            userOptional = userService.login(login, password);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                HttpSession session = request.getSession();
                session.setAttribute(RequestAttribute.USER, user);
                return new Router(PagePath.LIST_PAGE, Router.RouterType.FORWARD);
            } else {
                request.setAttribute(RequestAttribute.MESSAGE, "invalid login or password");
                return new Router(PagePath.LOGIN_PAGE, Router.RouterType.FORWARD);
            }

        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            return new Router(PagePath.ERROR_PAGE, Router.RouterType.FORWARD);
        }
    }
}
