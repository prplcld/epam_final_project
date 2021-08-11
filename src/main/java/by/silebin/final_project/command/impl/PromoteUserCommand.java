package by.silebin.final_project.command.impl;

import by.silebin.final_project.command.Command;
import by.silebin.final_project.command.Router;
import by.silebin.final_project.service.UserService;
import by.silebin.final_project.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class PromoteUserCommand implements Command {

    UserService userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        return null;
    }
}
