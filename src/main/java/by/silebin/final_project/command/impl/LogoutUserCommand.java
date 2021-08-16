package by.silebin.final_project.command.impl;

import by.silebin.final_project.command.Command;
import by.silebin.final_project.command.PagePath;
import by.silebin.final_project.command.Router;

import javax.servlet.http.HttpServletRequest;

public class LogoutUserCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        request.getSession().invalidate();
        return new Router(PagePath.GO_TO_COCKTAILS_LIST, Router.RouterType.REDIRECT);
    }
}
