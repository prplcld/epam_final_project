package by.silebin.final_project.command.impl;

import by.silebin.final_project.command.Command;
import by.silebin.final_project.command.Router;
import by.silebin.final_project.entity.Cocktail;
import by.silebin.final_project.model.service.CocktailService;
import by.silebin.final_project.model.service.impl.CocktailServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public class GetCocktailsListCommand implements Command {


    @Override
    public Router execute(HttpServletRequest request) {
        CocktailService cocktailService = new CocktailServiceImpl();
        List<Cocktail> cocktails = null;
        try {
            cocktails = cocktailService.getAllCocktails();
        } catch (IOException e) {
            e.printStackTrace();
        }
        request.setAttribute("cocktails", cocktails);
        return new Router("pages/list.jsp", Router.RouterType.FORWARD);
    }
}
