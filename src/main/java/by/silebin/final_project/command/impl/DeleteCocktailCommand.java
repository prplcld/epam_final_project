package by.silebin.final_project.command.impl;

import by.silebin.final_project.command.Command;
import by.silebin.final_project.command.PagePath;
import by.silebin.final_project.command.RequestAttribute;
import by.silebin.final_project.command.Router;
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

    CocktailService cocktailService = CocktailServiceImpl.getInstance();
    private static final Logger logger = LogManager.getLogger(DeleteCocktailCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String creator = request.getParameter("creator");

        if (user.getRole() != Role.ADMIN) {
            if (creator != null) {
                //FIXME
            }
        }

        int id = Integer.parseInt(request.getParameter("id"));
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
