package by.silebin.final_project.command.impl;

import by.silebin.final_project.command.*;
import by.silebin.final_project.entity.Role;
import by.silebin.final_project.entity.User;
import by.silebin.final_project.exception.ServiceException;
import by.silebin.final_project.service.UserService;
import by.silebin.final_project.service.impl.UserServiceImpl;
import by.silebin.final_project.validator.ParamValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ChangeUserRoleCommand implements Command {

    private final UserService userService = UserServiceImpl.getInstance();
    private static final Logger logger = LogManager.getLogger(ChangeUserRoleCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(RequestAttribute.USER);
        if (user == null || user.getRole() != Role.ADMIN) {
            request.setAttribute(RequestAttribute.MESSAGE, "you should login as admin");
            return new Router(PagePath.LOGIN_PAGE, Router.RouterType.FORWARD);
        }
        String userIdParam = request.getParameter(RequestParameter.USER_ID);
        String upgradeParam = request.getParameter(RequestParameter.UPGRADE);
        if (!ParamValidator.validateIntParam(userIdParam) || !ParamValidator.validateBooleanParam(upgradeParam)) {
            return new Router(PagePath.NOT_FOUND_PAGE, Router.RouterType.REDIRECT);
        }
        int userId = Integer.parseInt(userIdParam);
        boolean upgradeRole = Boolean.parseBoolean(upgradeParam);
        try {
            userService.updateUserRole(userId, upgradeRole ? Role.BARMAN : Role.USER);
        } catch (ServiceException e) {
            logger.error(e);
            request.getSession().setAttribute(RequestAttribute.EXCEPTION, e);
            return new Router(PagePath.ERROR_PAGE, Router.RouterType.REDIRECT);
        }
        return new Router(PagePath.GO_TO_USER_STAT, Router.RouterType.FORWARD);
    }
}
