package by.silebin.final_project.command.impl.go;

import by.silebin.final_project.command.Command;
import by.silebin.final_project.command.Router;

import javax.servlet.http.HttpServletRequest;

public class GoToLoginPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        return new Router("pages/login-page.jsp", Router.RouterType.FORWARD);
    }
}
