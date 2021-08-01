package by.silebin.final_project.command.impl;

import by.silebin.final_project.command.Command;
import by.silebin.final_project.command.Router;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        request.getSession().invalidate();
        return new Router("pages/list.jsp", Router.RouterType.FORWARD);
    }
}
