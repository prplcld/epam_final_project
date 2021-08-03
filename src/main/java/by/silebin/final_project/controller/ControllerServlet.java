package by.silebin.final_project.controller;

import by.silebin.final_project.command.Command;
import by.silebin.final_project.command.CommandProvider;
import by.silebin.final_project.command.PagePath;
import by.silebin.final_project.command.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllerServlet", urlPatterns = "/controller")
public class ControllerServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(ControllerServlet.class);

    private final CommandProvider commandProvider = CommandProvider.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter("command");
        Command command = commandProvider.getCommand(commandName);
        Router router = command.execute(request);
        switch (router.getRouterType()) {
            case FORWARD:
                RequestDispatcher dispatcher = request.getRequestDispatcher(router.getPagePath());
                dispatcher.forward(request, response);
                break;
            case REDIRECT:
                response.sendRedirect(router.getPagePath());
                break;
            default:
                logger.error("incorrect router type " + router.getRouterType());
                response.sendRedirect(PagePath.ERROR_PAGE);
                break;
        }
    }
}
