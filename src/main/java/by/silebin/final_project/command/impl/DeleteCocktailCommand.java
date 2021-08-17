package by.silebin.final_project.command.impl;

import by.silebin.final_project.command.*;
import by.silebin.final_project.entity.Role;
import by.silebin.final_project.entity.User;
import by.silebin.final_project.exception.ServiceException;
import by.silebin.final_project.service.CocktailService;
import by.silebin.final_project.service.impl.CocktailServiceImpl;
import by.silebin.final_project.validator.ParamValidator;
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
        String creatorIdParam = request.getParameter(RequestParameter.CREATOR);
        if (!ParamValidator.validateIntParam(creatorIdParam)) {
            return new Router(PagePath.NOT_FOUND_PAGE, Router.RouterType.REDIRECT);
        }

        int creatorId = Integer.parseInt(creatorIdParam);

        if(user == null || (user.getRole() != Role.ADMIN && creatorId != user.getUserId())) {
            request.setAttribute(RequestAttribute.MESSAGE, "you should login as admin or creator of this cocktail");
            return new Router(PagePath.LOGIN_PAGE, Router.RouterType.FORWARD);
        }

        String idParam = request.getParameter(RequestParameter.ID);
        if (!ParamValidator.validateIntParam(idParam)) {
            return new Router(PagePath.NOT_FOUND_PAGE, Router.RouterType.REDIRECT);
        }
        int id = Integer.parseInt(idParam);
        try {
            cocktailService.deleteCocktail(id);
            return new Router(PagePath.GO_TO_COCKTAILS_LIST, Router.RouterType.REDIRECT);
        } catch (ServiceException e) {
            logger.error(e);
            request.getSession().setAttribute(RequestAttribute.EXCEPTION, e);
            return new Router(PagePath.ERROR_PAGE, Router.RouterType.REDIRECT);
        }
    }
}
