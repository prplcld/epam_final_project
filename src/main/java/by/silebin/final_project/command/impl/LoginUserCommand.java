package by.silebin.final_project.command.impl;

import by.silebin.final_project.command.Command;
import by.silebin.final_project.command.PagePath;
import by.silebin.final_project.command.Router;
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

    UserService userService = new UserServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        String login = request.getParameter("username");
        String password = request.getParameter("password");

        Optional<User> userOptional;

        try {
            userOptional = userService.login(login, password);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                router = new Router(PagePath.LIST_PAGE, Router.RouterType.FORWARD);
            } else {
                request.setAttribute("message", "invalid login or password");
                router = new Router(PagePath.LOGIN_PAGE, Router.RouterType.FORWARD);
            }

        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute("exception", e);
            router = new Router(PagePath.ERROR_PAGE, Router.RouterType.FORWARD);
        }

        return router;
    }
}
