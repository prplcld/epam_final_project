package by.silebin.final_project.command.impl.ajax;

import by.silebin.final_project.command.AjaxCommand;
import by.silebin.final_project.entity.Ingredient;
import by.silebin.final_project.exception.ServiceException;
import by.silebin.final_project.service.IngredientService;
import by.silebin.final_project.service.impl.IngredientServiceImpl;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetIngredientsAjaxCommand implements AjaxCommand {

    private static final Logger logger = LogManager.getLogger(GetIngredientsAjaxCommand.class);

    IngredientService ingredientService = new IngredientServiceImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Ingredient> ingredients = ingredientService.getIngredients();
            String json = new Gson().toJson(ingredients);
            response.getWriter().append(json);
        } catch (ServiceException | IOException e) {
           logger.error(e);
        }
    }
}
