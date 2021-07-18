package by.silebin.final_project.controller;

import by.silebin.final_project.entity.Cocktail;
import by.silebin.final_project.exception.ServiceException;
import by.silebin.final_project.service.CocktailService;
import by.silebin.final_project.service.impl.CocktailServiceImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ajaxServlet", urlPatterns = "/ajax")
public class AjaxServlet extends HttpServlet {
    CocktailService cocktailService = new CocktailServiceImpl();
    private static final int itemsPerPage = 8;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            double cocktailsAmount = cocktailService.getCocktailsAmount();
            double pages = Math.ceil(cocktailsAmount/itemsPerPage);
            response.getWriter().append(String.valueOf((int)pages));
        } catch (ServiceException e) {
            //FIXME send error to user?
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = Integer.parseInt(request.getParameter("page"));
        try {
            List<Cocktail> cocktails = cocktailService.getLimited((page-1) * itemsPerPage, itemsPerPage);
            String json = new Gson().toJson(cocktails);
            response.getWriter().append(json);
        } catch (ServiceException e) {
            //response.sendError(404); ????
            //FIXME
        }
    }
}
