package by.silebin.final_project.controller;

import by.silebin.final_project.entity.Cocktail;
import by.silebin.final_project.exception.ServiceException;
import by.silebin.final_project.model.service.CocktailService;
import by.silebin.final_project.model.service.impl.CocktailServiceImpl;
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            double cocktailsAmount = cocktailService.getCocktailsAmount();
            double pages = Math.ceil(cocktailsAmount/itemsPerPage);
            resp.getWriter().append(String.valueOf((int)pages));
        } catch (ServiceException e) {
            //FIXME
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = Integer.parseInt(req.getParameter("page"));
        try {
            List<Cocktail> cocktails = cocktailService.getLimited((page-1) * itemsPerPage, itemsPerPage);
            String json = new Gson().toJson(cocktails);
            resp.getWriter().append(json);
        } catch (ServiceException e) {
            //FIXME
        }
    }
}
