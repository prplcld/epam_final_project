package by.silebin.final_project.command.impl;

import by.silebin.final_project.command.*;
import by.silebin.final_project.entity.Cocktail;
import by.silebin.final_project.entity.Ingredient;
import by.silebin.final_project.entity.Role;
import by.silebin.final_project.entity.User;
import by.silebin.final_project.exception.ServiceException;
import by.silebin.final_project.service.CocktailService;
import by.silebin.final_project.service.IngredientService;
import by.silebin.final_project.service.impl.CocktailServiceImpl;
import by.silebin.final_project.service.impl.IngredientServiceImpl;
import by.silebin.final_project.util.CocktailImageEncoder;
import by.silebin.final_project.validator.ParamValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class EditCocktailCommand implements Command {

    private static final Logger logger = LogManager.getLogger(EditCocktailCommand.class);

    private final CocktailService cocktailService = CocktailServiceImpl.getInstance();
    private final IngredientService ingredientService = IngredientServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(RequestAttribute.USER);
        String creatorIdParam = request.getParameter(RequestParameter.ID);

        if (!ParamValidator.validateIntParam(creatorIdParam)) {
            return new Router(PagePath.NOT_FOUND_PAGE, Router.RouterType.REDIRECT);
        }

        int creatorId = Integer.parseInt(creatorIdParam);

        if (user == null || (user.getRole() != Role.ADMIN && creatorId != user.getUserId())) {
            request.setAttribute(RequestAttribute.MESSAGE, "you should login as admin or creator of this cocktail");
            return new Router(PagePath.LOGIN_PAGE, Router.RouterType.FORWARD);
        }

        String idParam = request.getParameter(RequestParameter.ID);
        if (!ParamValidator.validateIntParam(idParam)) {
            return new Router(PagePath.NOT_FOUND_PAGE, Router.RouterType.REDIRECT);
        }
        int id = Integer.parseInt(idParam);

        try {
            Optional<Cocktail> cocktailOptional = cocktailService.getById(id);
            if (cocktailOptional.isPresent()) {
                Cocktail cocktail = cocktailOptional.get();
                CocktailImageEncoder.encodeImage(cocktail);
                request.setAttribute(RequestAttribute.COCKTAIL, cocktail);
                List<Ingredient> ingredients = ingredientService.getIngredientsForCocktail(cocktail.getCocktailId());
                request.setAttribute(RequestAttribute.INGREDIENTS, ingredients);
                List<Ingredient> allIngredients = ingredientService.getIngredients();
                request.setAttribute(RequestAttribute.ALL_INGREDIENTS, allIngredients);
                return new Router(PagePath.EDIT_COCKTAIL, Router.RouterType.FORWARD);
            } else {
                request.setAttribute(RequestAttribute.MESSAGE, "cocktail not found");
                return new Router(PagePath.NOT_FOUND_PAGE, Router.RouterType.FORWARD);
            }
        } catch (ServiceException | IOException e) {
            logger.error(e);
            request.getSession().setAttribute(RequestAttribute.EXCEPTION, e);
            return new Router(PagePath.ERROR_PAGE, Router.RouterType.REDIRECT);
        }
    }
}
