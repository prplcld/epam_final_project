package by.silebin.final_project.command.impl;

import by.silebin.final_project.command.*;
import by.silebin.final_project.entity.Mark;
import by.silebin.final_project.entity.User;
import by.silebin.final_project.exception.ServiceException;
import by.silebin.final_project.service.MarkService;
import by.silebin.final_project.service.impl.MarkServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class RateUserCommand implements Command {

    private static final Logger logger = LogManager.getLogger(RateUserCommand.class);
    private final MarkService markService = MarkServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter(RequestParameter.ID));
        User user = (User) request.getSession().getAttribute(RequestAttribute.USER);
        try {

            if (user == null) {
                request.setAttribute(RequestAttribute.MESSAGE, "you should log in to rate user");
                return new Router(PagePath.LOGIN_PAGE, Router.RouterType.FORWARD);
            } else {
                Mark mark = new Mark();
                mark.setTargetUserId(id);
                mark.setMarkUserId(user.getUserId());
                int rating = Integer.parseInt(request.getParameter(RequestParameter.RATING));
                mark.setMark(rating);
                markService.saveMark(mark);
            }
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            return new Router(PagePath.ERROR_PAGE, Router.RouterType.FORWARD);
        }
        return new Router(PagePath.GO_TO_PROFILE + id, Router.RouterType.FORWARD);
    }
}
