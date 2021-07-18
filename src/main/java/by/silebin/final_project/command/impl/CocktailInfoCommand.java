package by.silebin.final_project.command.impl;

import by.silebin.final_project.command.Command;
import by.silebin.final_project.command.PagePath;
import by.silebin.final_project.command.Router;
import by.silebin.final_project.entity.Cocktail;
import by.silebin.final_project.exception.ServiceException;
import by.silebin.final_project.service.CocktailService;
import by.silebin.final_project.service.impl.CocktailServiceImpl;
import by.silebin.final_project.util.CocktailImageEncoder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

public class CocktailInfoCommand implements Command {

    CocktailService cocktailService = new CocktailServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            Optional<Cocktail> cocktailOptional = cocktailService.getById(id);
            if (cocktailOptional.isPresent()) {
                Cocktail cocktail = cocktailOptional.get();
                CocktailImageEncoder.encodeImage(cocktail);
                request.setAttribute("cocktail", cocktail);
                //TODO get ingredients for cocktail
                router = new Router(PagePath.COCKTAIL_PAGE, Router.RouterType.FORWARD);
            }
            else {
                request.setAttribute("message", "cocktail not found");
                router = new Router(PagePath.NOT_FOUND, Router.RouterType.FORWARD);
            }
        } catch (ServiceException | IOException e) {
            //FIXME logger
            request.setAttribute("exception", e);
            router =  new Router(PagePath.ERROR_PAGE, Router.RouterType.FORWARD);
        }
        return router;
    }
}
