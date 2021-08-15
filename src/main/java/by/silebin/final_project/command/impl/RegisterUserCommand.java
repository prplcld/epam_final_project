package by.silebin.final_project.command.impl;

import by.silebin.final_project.command.*;
import by.silebin.final_project.exception.ServiceException;
import by.silebin.final_project.service.UserService;
import by.silebin.final_project.service.impl.UserServiceImpl;
import by.silebin.final_project.validator.EmailValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class RegisterUserCommand implements Command {


    private static final Logger logger = LogManager.getLogger(RegisterUserCommand.class);
    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {

        String login = request.getParameter(RequestParameter.USERNAME);
        String email = request.getParameter(RequestParameter.EMAIL);
        String password = request.getParameter(RequestParameter.PASSWORD);
        String confirmPassword = request.getParameter(RequestParameter.CONFIRM_PASSWORD);

        if(!EmailValidator.isValid(email)) {
            request.setAttribute(RequestAttribute.MESSAGE, "invalid email");
            return new Router(PagePath.REGISTER_PAGE, Router.RouterType.FORWARD);
        }
        else {
            if (!password.equals(confirmPassword)) {
                request.setAttribute(RequestAttribute.MESSAGE, "passwords do not match");
                return new Router(PagePath.REGISTER_PAGE, Router.RouterType.FORWARD);
            }
            else {
                try {
                    userService.register(login, password, email);
                    return new Router(PagePath.LOGIN_PAGE, Router.RouterType.REDIRECT);
                } catch (ServiceException e) {
                    logger.error(e);
                    request.setAttribute(RequestAttribute.EXCEPTION, e);
                    return new Router(PagePath.ERROR_PAGE, Router.RouterType.FORWARD);
                }
            }
        }
    }
}
