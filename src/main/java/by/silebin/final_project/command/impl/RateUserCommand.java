package by.silebin.final_project.command.impl;

import by.silebin.final_project.command.*;
import by.silebin.final_project.entity.Mark;
import by.silebin.final_project.entity.User;
import by.silebin.final_project.exception.ServiceException;
import by.silebin.final_project.service.MarkService;
import by.silebin.final_project.service.impl.MarkServiceImpl;
import by.silebin.final_project.validator.ParamValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class RateUserCommand implements Command {

    private static final Logger logger = LogManager.getLogger(RateUserCommand.class);
    private final MarkService markService = MarkServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        String idParam = request.getParameter(RequestParameter.ID);
        if (!ParamValidator.validateIntParam(idParam)) {
            return new Router(PagePath.NOT_FOUND_PAGE, Router.RouterType.REDIRECT);
        }
        int id = Integer.parseInt(idParam);

        User user = (User) request.getSession().getAttribute(RequestAttribute.USER);
        try {

            if (user == null) {
                request.getSession().setAttribute(RequestAttribute.MESSAGE, "you should log in to rate user");
                return new Router(PagePath.LOGIN_PAGE, Router.RouterType.REDIRECT);
            } else {
                Mark mark = new Mark();
                mark.setTargetUserId(id);
                mark.setMarkUserId(user.getUserId());
                String ratingParam = request.getParameter(RequestParameter.RATING);
                if (!ParamValidator.validateIntParam(ratingParam)) {
                    return new Router(PagePath.NOT_FOUND_PAGE, Router.RouterType.REDIRECT);
                }
                int rating = Integer.parseInt(ratingParam);
                mark.setMark(rating);
                markService.saveMark(mark);
            }
        } catch (ServiceException e) {
            logger.error(e);
            request.getSession().setAttribute(RequestAttribute.EXCEPTION, e);
            return new Router(PagePath.ERROR_PAGE, Router.RouterType.REDIRECT);
        }
        return new Router(PagePath.GO_TO_PROFILE + id, Router.RouterType.REDIRECT);
    }
}
