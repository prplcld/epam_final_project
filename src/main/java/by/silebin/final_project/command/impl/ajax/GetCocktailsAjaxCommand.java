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

public class GetCocktailsAjaxCommand implements AjaxCommand {

    private static final Logger logger = LogManager.getLogger(GetCocktailsAjaxCommand.class);

    CocktailService cocktailService = new CocktailServiceImpl();
    private static final int itemsPerPage = 8;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        int page = Integer.parseInt(request.getParameter("page"));
        try {
            List<Cocktail> cocktails = cocktailService.getLimited((page - 1) * itemsPerPage, itemsPerPage);
            String json = new Gson().toJson(cocktails);
            response.getWriter().append(json);
        } catch (ServiceException | IOException e) {
            logger.error(e);
        }
    }
}
