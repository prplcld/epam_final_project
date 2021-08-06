package by.silebin.final_project.command.impl;

import by.silebin.final_project.command.*;
import by.silebin.final_project.exception.ServiceException;
import by.silebin.final_project.service.UserService;
import by.silebin.final_project.service.impl.UserServiceImpl;
import by.silebin.final_project.validator.EmailValidator;

import javax.servlet.http.HttpServletRequest;

public class RegisterUserCommand implements Command {

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        String login = request.getParameter(RequestParameter.USERNAME);
        String email = request.getParameter(RequestParameter.EMAIL);
        String password = request.getParameter(RequestParameter.PASSWORD);
        String confirmPassword = request.getParameter(RequestParameter.CONFIRM_PASSWORD);

        if(!EmailValidator.isValid(email)) {
            request.setAttribute(RequestAttribute.MESSAGE, "invalid email");
            router = new Router(PagePath.REGISTER_PAGE, Router.RouterType.FORWARD);
        }
        else {
            if (!password.equals(confirmPassword)) {
                request.setAttribute(RequestAttribute.MESSAGE, "passwords do not match");
                router = new Router(PagePath.REGISTER_PAGE, Router.RouterType.FORWARD);
            }
            else {
                try {
                    userService.register(login, password, email);
                    router = new Router(PagePath.LOGIN_PAGE, Router.RouterType.FORWARD);
                } catch (ServiceException e) {
                    //FIXME
                    router = new Router(PagePath.ERROR_PAGE, Router.RouterType.REDIRECT);
                }
            }
        }

        return router;
    }
}
