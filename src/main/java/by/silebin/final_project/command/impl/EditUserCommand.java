package by.silebin.final_project.command.impl;

import by.silebin.final_project.command.*;
import by.silebin.final_project.entity.User;
import by.silebin.final_project.validator.ParamValidator;

import javax.servlet.http.HttpServletRequest;

public class EditUserCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        String idParam = request.getParameter(RequestParameter.ID);
        User user = (User) request.getSession().getAttribute(RequestAttribute.USER);

        if (!ParamValidator.validateIntParam(idParam)) {
            return new Router(PagePath.NOT_FOUND_PAGE, Router.RouterType.REDIRECT);
        }
        int id = Integer.parseInt(idParam);

        if (user == null || user.getUserId() != id) {
            request.setAttribute(RequestAttribute.MESSAGE, "you should login as this user to edit profile");
            return new Router(PagePath.LOGIN_PAGE, Router.RouterType.FORWARD);
        }
        request.setAttribute(RequestAttribute.USER, user);
        return new Router(PagePath.EDIT_USER, Router.RouterType.FORWARD);
    }
}
