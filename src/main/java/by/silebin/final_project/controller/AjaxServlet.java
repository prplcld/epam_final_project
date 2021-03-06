package by.silebin.final_project.controller;

import by.silebin.final_project.command.AjaxCommand;
import by.silebin.final_project.command.AjaxCommandProvider;
import by.silebin.final_project.command.RequestParameter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ajaxServlet", urlPatterns = "/ajax")
public class AjaxServlet extends HttpServlet {

    AjaxCommandProvider ajaxCommandProvider = AjaxCommandProvider.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String commandName = request.getParameter(RequestParameter.COMMAND);
        AjaxCommand command = ajaxCommandProvider.getCommand(commandName);
        command.execute(request, response);
    }
}
