package by.silebin.final_project.command.impl;

import by.silebin.final_project.command.Command;
import by.silebin.final_project.command.Router;
import by.silebin.final_project.entity.User;
import by.silebin.final_project.model.service.UserService;
import by.silebin.final_project.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginUserCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        UserService userService = new UserServiceImpl();
        String login = request.getParameter("username");
        String password = request.getParameter("password");

        Optional<User> userOptional = userService.login(login, password);
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            return new Router("/controller?command=go_to_list_page", Router.RouterType.FORWARD);
        }
        return new Router("pages/login.jsp", Router.RouterType.FORWARD);
    }
}
