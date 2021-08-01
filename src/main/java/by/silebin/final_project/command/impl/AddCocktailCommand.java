package by.silebin.final_project.command.impl;

import by.silebin.final_project.command.Command;
import by.silebin.final_project.command.PagePath;
import by.silebin.final_project.command.Router;
import by.silebin.final_project.entity.Cocktail;
import by.silebin.final_project.entity.User;
import by.silebin.final_project.exception.ServiceException;
import by.silebin.final_project.service.CocktailService;
import by.silebin.final_project.service.IngredientService;
import by.silebin.final_project.service.impl.CocktailServiceImpl;
import by.silebin.final_project.service.impl.IngredientServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AddCocktailCommand implements Command {

    CocktailService cocktailService = new CocktailServiceImpl();
    IngredientService ingredientService = new IngredientServiceImpl();

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
                        case "name" : cocktail.setName(item.getString());
                        break;
                        case "description" : cocktail.setDescription(item.getString());
                        break;
                        case "dropdown" : ingredients.add(Integer.parseInt(item.getString()));
                        break;
                        case "amount" : amounts.add(Integer.parseInt(item.getString()));
                        break;
                    }

                }
                else {
                    InputStream inputStream  = item.getInputStream();
                    cocktail.setIcon(inputStream);
                }
            }

            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            cocktail.setUserId(user.getUserId());
            int insertId = cocktailService.insert(cocktail);

            if (insertId == -1) {
                //FIXME error during insert
            } else {
                ingredientService.addIngredientsForCocktail(ingredients, amounts, insertId);
            }

        } catch (FileUploadException | IOException | ServiceException e) {
            e.printStackTrace();
        }

        return new Router(PagePath.LIST_PAGE, Router.RouterType.FORWARD);
    }
}
