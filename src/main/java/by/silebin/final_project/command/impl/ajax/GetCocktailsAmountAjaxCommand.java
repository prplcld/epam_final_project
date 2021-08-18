package by.silebin.final_project.command.impl.ajax;

import by.silebin.final_project.command.AjaxCommand;
import by.silebin.final_project.command.PagePath;
import by.silebin.final_project.command.RequestAttribute;
import by.silebin.final_project.exception.ServiceException;
import by.silebin.final_project.service.CocktailService;
import by.silebin.final_project.service.impl.CocktailServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetCocktailsAmountAjaxCommand implements AjaxCommand {

    private static final Logger logger = LogManager.getLogger(GetCocktailsAjaxCommand.class);

    private final CocktailService cocktailService = CocktailServiceImpl.getInstance();
    private static final int itemsPerPage = 8;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            double cocktailsAmount = cocktailService.getCocktailsAmount();
            double pages = Math.ceil(cocktailsAmount / itemsPerPage);
            response.getWriter().append(String.valueOf((int) pages));
        } catch (ServiceException | IOException e) {
            logger.error(e);
            request.getSession().setAttribute(RequestAttribute.EXCEPTION, e);
            response.sendRedirect(PagePath.ERROR_PAGE);
        }
    }
}
