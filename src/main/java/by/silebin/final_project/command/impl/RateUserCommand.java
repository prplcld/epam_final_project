package by.silebin.final_project.command.impl;

import by.silebin.final_project.command.*;
import by.silebin.final_project.entity.Mark;
import by.silebin.final_project.entity.User;
import by.silebin.final_project.exception.ServiceException;
import by.silebin.final_project.service.MarkService;
import by.silebin.final_project.service.impl.MarkServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class RateUserCommand implements Command {

    private final MarkService markService = MarkServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter(RequestParameter.ID));
        User user = (User) request.getSession().getAttribute(RequestAttribute.USER);
        try {

            if (user == null) {
                //FIXME
            } else {
                Mark mark = new Mark();
                mark.setTargetUserId(id);
                mark.setMarkUserId(user.getUserId());
                int rating = Integer.parseInt(request.getParameter(RequestParameter.RATING));
                mark.setMark(rating);
                markService.saveMark(mark);
            }
        } catch (ServiceException e) {
            //FIXME
        }
        return new Router(PagePath.GO_TO_PROFILE + id, Router.RouterType.FORWARD);
    }
}
