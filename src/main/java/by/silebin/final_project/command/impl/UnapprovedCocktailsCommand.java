package by.silebin.final_project.command.impl;

import by.silebin.final_project.command.Command;
import by.silebin.final_project.command.PagePath;
import by.silebin.final_project.command.RequestAttribute;
import by.silebin.final_project.command.Router;
import by.silebin.final_project.entity.Cocktail;
import by.silebin.final_project.entity.Role;
import by.silebin.final_project.entity.User;
import by.silebin.final_project.exception.ServiceException;
import by.silebin.final_project.service.CocktailService;
import by.silebin.final_project.service.impl.CocktailServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class UnapprovedCocktailsCommand implements Command {

    private static final Logger logger = LogManager.getLogger(UnapprovedCocktailsCommand.class);
    private final CocktailService cocktailService = CocktailServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(RequestAttribute.USER);
        if(user == null || user.getRole() != Role.ADMIN) {
            request.setAttribute(RequestAttribute.MESSAGE, "you should log in as admin");
            return new Router(PagePath.LOGIN_PAGE, Router.RouterType.FORWARD);
        }

        try {
            List<Cocktail> cocktails = cocktailService.getUnapprovedCocktails();
            request.setAttribute(RequestAttribute.COCKTAILS, cocktails);
            return new Router(PagePath.COCKTAIL_APPROVAL_PAGE, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.error(e);
            request.getSession().setAttribute(RequestAttribute.EXCEPTION, e);
            return new Router(PagePath.ERROR_PAGE, Router.RouterType.REDIRECT);
        }
    }
}
