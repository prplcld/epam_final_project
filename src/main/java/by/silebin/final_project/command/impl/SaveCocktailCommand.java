package by.silebin.final_project.command.impl;

import by.silebin.final_project.command.*;
import by.silebin.final_project.entity.Cocktail;
import by.silebin.final_project.entity.Role;
import by.silebin.final_project.entity.User;
import by.silebin.final_project.exception.ServiceException;
import by.silebin.final_project.service.CocktailService;
import by.silebin.final_project.service.impl.CocktailServiceImpl;
import by.silebin.final_project.validator.CocktailValidator;
import by.silebin.final_project.validator.ParamValidator;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SaveCocktailCommand implements Command {

    private final CocktailService cocktailService = CocktailServiceImpl.getInstance();
    private static final Logger logger = LogManager.getLogger(SaveCocktailCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {

        try {
            List<Integer> ingredients = new ArrayList<>();
            List<Integer> amounts = new ArrayList<>();

            Cocktail cocktail = new Cocktail();
            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            for (FileItem item : items) {
                if (item.isFormField()) {
                    switch (item.getFieldName()) {
                        case RequestParameter.ID:
                            if (!ParamValidator.validateIntParam(item.getString())) {
                                request.getSession().setAttribute(RequestAttribute.MESSAGE, "not found");
                                return new Router(PagePath.NOT_FOUND_PAGE, Router.RouterType.REDIRECT);
                            }
                            cocktail.setCocktailId(Integer.parseInt(item.getString()));
                            break;
                        case RequestParameter.NAME:
                            String name = new String(item.getString().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
                            if (!CocktailValidator.validateName(name)) {
                                request.getSession().setAttribute(RequestAttribute.MESSAGE, "invalid name");
                                return new Router(PagePath.GO_TO_EDIT_COCKTAIL + cocktail.getCocktailId(), Router.RouterType.REDIRECT);
                            }
                            cocktail.setName(name);
                            break;
                        case RequestParameter.DESCRIPTION:
                            cocktail.setDescription(new String(item.getString().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                            break;
                        case RequestParameter.DROPDOWN:
                            if (!ParamValidator.validateIntParam(item.getString())) {
                                request.getSession().setAttribute(RequestAttribute.MESSAGE, "invalid parameter");
                                return new Router(PagePath.GO_TO_EDIT_COCKTAIL + cocktail.getCocktailId(), Router.RouterType.REDIRECT);
                            }
                            ingredients.add(Integer.parseInt(item.getString()));
                            break;
                        case RequestParameter.AMOUNT:
                            String itemStr = item.getString();
                            if (!ParamValidator.validateIntParam(itemStr)) {
                                request.getSession().setAttribute(RequestAttribute.MESSAGE, "invalid parameter");
                                return new Router(PagePath.GO_TO_EDIT_COCKTAIL + cocktail.getCocktailId(), Router.RouterType.REDIRECT);
                            }
                            amounts.add(Integer.parseInt(item.getString()));
                            break;
                    }

                } else {
                    InputStream inputStream = item.getInputStream();
                    cocktail.setIcon(inputStream);
                }
            }

            HttpSession session = request.getSession();
            User user = (User) session.getAttribute(RequestAttribute.USER);
            if (user == null) {
                request.getSession().setAttribute(RequestAttribute.MESSAGE, "you should log in to edit cocktail");
                return new Router(PagePath.LOGIN_PAGE, Router.RouterType.REDIRECT);
            }
            cocktail.setUserId(user.getUserId());
            cocktail.setApproved(user.getRole() != Role.USER);
            cocktailService.updateCocktailWithIngredients(cocktail, ingredients, amounts);
            return new Router(PagePath.GO_TO_COCKTAIL_PAGE + cocktail.getCocktailId(), Router.RouterType.REDIRECT);

        } catch (FileUploadException | IOException | ServiceException e) {
            logger.error(e);
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            return new Router(PagePath.ERROR_PAGE, Router.RouterType.REDIRECT);
        }

    }
}
