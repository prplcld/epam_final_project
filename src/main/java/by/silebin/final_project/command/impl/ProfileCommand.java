package by.silebin.final_project.command.impl;

import by.silebin.final_project.command.*;
import by.silebin.final_project.entity.Cocktail;
import by.silebin.final_project.entity.Mark;
import by.silebin.final_project.entity.User;
import by.silebin.final_project.exception.ServiceException;
import by.silebin.final_project.service.CocktailService;
import by.silebin.final_project.service.MarkService;
import by.silebin.final_project.service.UserService;
import by.silebin.final_project.service.impl.CocktailServiceImpl;
import by.silebin.final_project.service.impl.MarkServiceImpl;
import by.silebin.final_project.service.impl.UserServiceImpl;
import by.silebin.final_project.validator.ParamValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public class ProfileCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ProfileCommand.class);

    private final CocktailService cocktailService = CocktailServiceImpl.getInstance();
    private final UserService userService = UserServiceImpl.getInstance();
    private final MarkService markService = MarkServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {

        String idParam = request.getParameter(RequestParameter.ID);
        if (!ParamValidator.validateIntParam(idParam)) {
            return new Router(PagePath.NOT_FOUND_PAGE, Router.RouterType.REDIRECT);
        }
        int id = Integer.parseInt(idParam);

        try {
            Optional<User> userOptional = userService.getById(id);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                request.setAttribute(RequestAttribute.USER, user);
                List<Cocktail> cocktails = cocktailService.getByUserId(id);
                request.setAttribute(RequestAttribute.COCKTAILS, cocktails);
                int mark = markService.getAverageUserMark(id);
                request.setAttribute(RequestAttribute.MARK, mark);
                User loggedInUser = (User) request.getSession().getAttribute(RequestAttribute.USER);
                if (loggedInUser != null && loggedInUser.getUserId() != user.getUserId()) {
                    Optional<Mark> markOptional = markService.getMark(user.getUserId(), loggedInUser.getUserId());
                    if (markOptional.isPresent()) {
                        Mark loggedInUserMark = markOptional.get();
                        request.setAttribute(RequestAttribute.USER_MARK, loggedInUserMark);
                    }
                }
                return new Router(PagePath.PROFILE_PAGE, Router.RouterType.FORWARD);
            } else {
                request.setAttribute(RequestAttribute.MESSAGE, "user not found");
                return new Router(PagePath.NOT_FOUND_PAGE, Router.RouterType.FORWARD);
            }
        } catch (ServiceException e) {
            logger.error(e);
            request.getSession().setAttribute(RequestAttribute.EXCEPTION, e);
            return new Router(PagePath.ERROR_PAGE, Router.RouterType.REDIRECT);
        }

    }
}
