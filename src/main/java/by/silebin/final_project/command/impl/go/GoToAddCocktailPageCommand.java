package by.silebin.final_project.command.impl.go;

import by.silebin.final_project.command.Command;
import by.silebin.final_project.command.PagePath;
import by.silebin.final_project.command.RequestAttribute;
import by.silebin.final_project.command.Router;
import by.silebin.final_project.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class GoToAddCocktailPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(RequestAttribute.USER);
        if (user == null){
            request.setAttribute(RequestAttribute.MESSAGE, "you should log in to create cocktail");
            return new Router(PagePath.LOGIN_PAGE, Router.RouterType.FORWARD);
        }
        return new Router(PagePath.ADD_COCKTAIL_PAGE, Router.RouterType.FORWARD);
    }
}
