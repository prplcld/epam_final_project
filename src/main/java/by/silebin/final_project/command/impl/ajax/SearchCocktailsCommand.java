package by.silebin.final_project.command.impl.ajax;

import by.silebin.final_project.command.AjaxCommand;
import by.silebin.final_project.entity.Cocktail;
import by.silebin.final_project.exception.ServiceException;
import by.silebin.final_project.service.CocktailService;
import by.silebin.final_project.service.impl.CocktailServiceImpl;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SearchCocktailsCommand implements AjaxCommand {

    private static final Logger logger = LogManager.getLogger(SearchCocktailsCommand.class);

    CocktailService cocktailService = new CocktailServiceImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String search = request.getParameter("value");
            List<Cocktail> cocktails = cocktailService.getByNameLike(search);
            String json = new Gson().toJson(cocktails);
            response.getWriter().append(json);
        } catch (ServiceException | IOException e) {
            logger.error(e);
        }
    }
}
