package by.silebin.final_project.command.impl;

import by.silebin.final_project.command.Command;
import by.silebin.final_project.command.RequestParameter;
import by.silebin.final_project.command.Router;
import by.silebin.final_project.entity.Role;
import by.silebin.final_project.exception.ServiceException;
import by.silebin.final_project.service.UserService;
import by.silebin.final_project.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class ChangeUserRoleCommand implements Command {

    UserService userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter(RequestParameter.USER_ID));
        boolean upgradeRole = Boolean.parseBoolean(request.getParameter(request.getParameter("upgrade")));
        try {
            userService.updateUserRole(userId, upgradeRole ? Role.BARMAN : Role.USER);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return null;
    }
}
