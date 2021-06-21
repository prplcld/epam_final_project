package by.silebin.final_project.controller;

import by.silebin.final_project.entity.User;
import by.silebin.final_project.model.dao.UserDao;
import by.silebin.final_project.model.dao.impl.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "loginServlet", urlPatterns = "/login")
public class TestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("kek");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserDao userDao = UserDaoImpl.getInstance();
        User user = userDao.login(username, password).get();
        HttpSession session = request.getSession();
        session.setAttribute("login", user.getLogin());

        request.getRequestDispatcher("/pages/login_2.jsp").forward(request, response);
    }
}
