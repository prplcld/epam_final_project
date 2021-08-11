package by.silebin.final_project.command.impl;

import by.silebin.final_project.command.Command;
import by.silebin.final_project.command.PagePath;
import by.silebin.final_project.command.RequestAttribute;
import by.silebin.final_project.command.Router;
import by.silebin.final_project.entity.dto.UserStatDto;
import by.silebin.final_project.exception.ServiceException;
import by.silebin.final_project.service.UserService;
import by.silebin.final_project.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UsersStatCommand implements Command {

    private static final Logger logger = LogManager.getLogger(UsersStatCommand.class);
    UserService userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        try {
            List<UserStatDto> stat = userService.getUsersStat();
            request.setAttribute(RequestAttribute.STAT, stat);
            return new Router(PagePath.USER_STAT, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            return new Router(PagePath.ERROR_PAGE, Router.RouterType.FORWARD);
        }
    }
}
