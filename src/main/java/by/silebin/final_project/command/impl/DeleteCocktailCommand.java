package by.silebin.final_project.command.impl;

import by.silebin.final_project.command.*;
import by.silebin.final_project.entity.Role;
import by.silebin.final_project.entity.User;
import by.silebin.final_project.exception.ServiceException;
import by.silebin.final_project.service.CocktailService;
import by.silebin.final_project.service.impl.CocktailServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DeleteCocktailCommand implements Command {

    private final CocktailService cocktailService = CocktailServiceImpl.getInstance();
    private static final Logger logger = LogManager.getLogger(DeleteCocktailCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(RequestAttribute.USER);
        int creatorId = Integer.parseInt(request.getParameter(RequestParameter.CREATOR));

        if(user.getRole() != Role.ADMIN || creatorId != user.getUserId()) {
            request.setAttribute(RequestAttribute.MESSAGE, "you should login as admin or creator of this cocktail");
            return new Router(PagePath.LOGIN_PAGE, Router.RouterType.FORWARD);
        }

        int id = Integer.parseInt(request.getParameter(RequestParameter.ID));
        try {
            cocktailService.deleteCocktail(id);
            return new Router(PagePath.LIST_PAGE, Router.RouterType.REDIRECT);
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            return new Router(PagePath.ERROR_PAGE, Router.RouterType.FORWARD);
        }
    }
}
