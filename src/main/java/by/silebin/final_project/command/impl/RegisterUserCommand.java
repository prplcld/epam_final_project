package by.silebin.final_project.command.impl;

import by.silebin.final_project.command.Command;
import by.silebin.final_project.command.PagePath;
import by.silebin.final_project.command.Router;
import by.silebin.final_project.exception.ServiceException;
import by.silebin.final_project.service.UserService;
import by.silebin.final_project.service.impl.UserServiceImpl;
import by.silebin.final_project.validator.EmailValidator;

import javax.servlet.http.HttpServletRequest;

public class RegisterUserCommand implements Command {

    UserService userService = new UserServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        if(!EmailValidator.isValid(email)) {
            request.setAttribute("message", "invalid email");
            router = new Router(PagePath.REGISTER_PAGE, Router.RouterType.FORWARD);
        }
        else {
            if (!password.equals(confirmPassword)) {
                request.setAttribute("message", "passwords do not match");
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
