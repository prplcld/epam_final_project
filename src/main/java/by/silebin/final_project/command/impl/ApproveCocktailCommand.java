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

public class ApproveCocktailCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ApproveCocktailCommand.class);
    private final CocktailService cocktailService = CocktailServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(RequestAttribute.USER);

        if(user == null || user.getRole() != Role.ADMIN) {
            request.setAttribute(RequestAttribute.MESSAGE, "you should log in as admin");
            return new Router(PagePath.LOGIN_PAGE, Router.RouterType.FORWARD);
        }

        int id = Integer.parseInt(request.getParameter(RequestParameter.ID));
        try {
            cocktailService.approveCocktail(id);
            return new Router(PagePath.GO_TO_APPROVE_COCKTAILS, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            return new Router(PagePath.ERROR_PAGE, Router.RouterType.FORWARD);
        }
    }
}
