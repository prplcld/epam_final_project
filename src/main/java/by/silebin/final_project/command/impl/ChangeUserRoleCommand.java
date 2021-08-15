package by.silebin.final_project.command.impl;

import by.silebin.final_project.command.*;
import by.silebin.final_project.entity.Role;
import by.silebin.final_project.exception.ServiceException;
import by.silebin.final_project.service.UserService;
import by.silebin.final_project.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ChangeUserRoleCommand implements Command {

    private final UserService userService = UserServiceImpl.getInstance();
    private static final Logger logger = LogManager.getLogger(ChangeUserRoleCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter(RequestParameter.USER_ID));
        boolean upgradeRole = Boolean.parseBoolean(request.getParameter(RequestParameter.UPGRADE));
        try {

            userService.updateUserRole(userId, upgradeRole ? Role.BARMAN : Role.USER);
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            return new Router(PagePath.ERROR_PAGE, Router.RouterType.FORWARD);
        }
        return new Router(PagePath.GO_TO_USER_STAT, Router.RouterType.FORWARD);
    }
}
