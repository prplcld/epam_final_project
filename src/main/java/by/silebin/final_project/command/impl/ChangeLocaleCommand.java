package by.silebin.final_project.command.impl;

import by.silebin.final_project.command.Command;
import by.silebin.final_project.command.RequestAttribute;
import by.silebin.final_project.command.RequestParameter;
import by.silebin.final_project.command.Router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeLocaleCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession SESSION = request.getSession();
        String locale = request.getParameter(RequestParameter.LOCALE);
        String previous_request = (String) SESSION.getAttribute(RequestAttribute.PREV_REQUEST);
        SESSION.setAttribute(RequestAttribute.LOCALE, locale);
        Router router = new Router(previous_request, Router.RouterType.REDIRECT);
        return router;
    }
}
