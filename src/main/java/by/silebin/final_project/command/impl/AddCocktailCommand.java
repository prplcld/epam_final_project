package by.silebin.final_project.command.impl;

import by.silebin.final_project.command.*;
import by.silebin.final_project.entity.Cocktail;
import by.silebin.final_project.entity.Role;
import by.silebin.final_project.entity.User;
import by.silebin.final_project.exception.ServiceException;
import by.silebin.final_project.service.CocktailService;
import by.silebin.final_project.service.impl.CocktailServiceImpl;
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
import java.util.ArrayList;
import java.util.List;

public class AddCocktailCommand implements Command {

    private final CocktailService cocktailService = CocktailServiceImpl.getInstance();
    private static final Logger logger = LogManager.getLogger(AddCocktailCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {

        try {
            List<Integer> ingredients = new ArrayList<>();
            List<Integer> amounts = new ArrayList<>();

            Cocktail cocktail = new Cocktail();
            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            for(FileItem item : items) {
                if (item.isFormField()) {
                    switch (item.getFieldName()) {
                        case RequestParameter.NAME : cocktail.setName(item.getString());
                        break;
                        case RequestParameter.DESCRIPTION: cocktail.setDescription(item.getString());
                        break;
                        case RequestParameter.DROPDOWN : ingredients.add(Integer.parseInt(item.getString()));
                        break;
                        case RequestParameter.AMOUNT :
                            String itemStr = item.getString();
                            if (itemStr.equals("")){
                                request.setAttribute(RequestAttribute.MESSAGE, "amount field shouldn't be empty");
                                return new Router(PagePath.ADD_COCKTAIL_PAGE, Router.RouterType.FORWARD);
                            }
                            amounts.add(Integer.parseInt(item.getString()));
                        break;
                    }
                }
                else {
                    InputStream inputStream  = item.getInputStream();
                    cocktail.setIcon(inputStream);
                }
            }

            HttpSession session = request.getSession();
            User user = (User) session.getAttribute(RequestAttribute.USER);
            if(user == null) {
                request.setAttribute(RequestAttribute.MESSAGE, "you should log in to create cocktail");
                return new Router(PagePath.LOGIN_PAGE, Router.RouterType.FORWARD);
            }
            cocktail.setUserId(user.getUserId());
            cocktail.setApproved(user.getRole() != Role.USER);
            int insertId = cocktailService.insertCocktailWithIngredients(cocktail, ingredients, amounts);
            return new Router(PagePath.GO_TO_COCKTAIL_PAGE + insertId, Router.RouterType.REDIRECT);

        } catch (FileUploadException | IOException | ServiceException e) {
            logger.error(e);
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            return new Router(PagePath.ERROR_PAGE, Router.RouterType.FORWARD);
        }

    }
}
