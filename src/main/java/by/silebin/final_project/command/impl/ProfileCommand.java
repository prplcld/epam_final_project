package by.silebin.final_project.command.impl;

import by.silebin.final_project.command.Command;
import by.silebin.final_project.command.PagePath;
import by.silebin.final_project.command.Router;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public class ProfileCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ProfileCommand.class);

    CocktailService cocktailService = new CocktailServiceImpl();
    UserService userService = new UserServiceImpl();
    MarkService markService = new MarkServiceImpl();


    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            Optional<User> userOptional = userService.getById(id);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                request.setAttribute("user", user);
                List<Cocktail> cocktails = cocktailService.getByUserId(id);
                request.setAttribute("cocktails", cocktails);
                int mark = markService.getAverageUserMark(id);
                request.setAttribute("mark", mark);
                User loggedInUser = (User)request.getSession().getAttribute("user");
                if (loggedInUser != null && loggedInUser.getUserId() != user.getUserId()) {
                    Optional<Mark> markOptional = markService.getMark(user.getUserId(), loggedInUser.getUserId());
                    if (markOptional.isPresent()) {
                        Mark loggedInUserMark = markOptional.get();
                        request.setAttribute("userMark", loggedInUserMark);
                    }
                }
                router = new Router(PagePath.PROFILE_PAGE, Router.RouterType.FORWARD);
            }
            else {
                request.setAttribute("message", "user not found");
                router = new Router(PagePath.ERROR_PAGE, Router.RouterType.FORWARD);
            }
        } catch (ServiceException e) {
            logger.error(e);
            request.getSession().setAttribute("exception", "error");
            router = new Router(PagePath.ERROR_PAGE, Router.RouterType.REDIRECT);
        }
        return router;
    }
}
