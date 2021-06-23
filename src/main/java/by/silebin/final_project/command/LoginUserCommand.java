package by.silebin.final_project.command;

import by.silebin.final_project.entity.User;
import by.silebin.final_project.model.service.UserService;
import by.silebin.final_project.model.service.impl.UserServiceImpl;
import com.mysql.cj.xdevapi.Session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

public class LoginUserCommand implements Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserServiceImpl();
        String login = request.getParameter("username");
        String password = request.getParameter("password");

        Optional<User> userOptional = userService.login(login, password);
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            request.setAttribute("login", user.getLogin());
            request.getRequestDispatcher("pages/login.jsp").forward(request, response);
        }
    }
}
