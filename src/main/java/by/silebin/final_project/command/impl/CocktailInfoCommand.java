package by.silebin.final_project.command.impl;

import by.silebin.final_project.command.*;
import by.silebin.final_project.entity.Cocktail;
import by.silebin.final_project.entity.Ingredient;
import by.silebin.final_project.entity.dto.CommentDto;
import by.silebin.final_project.exception.ServiceException;
import by.silebin.final_project.service.CocktailService;
import by.silebin.final_project.service.CommentService;
import by.silebin.final_project.service.IngredientService;
import by.silebin.final_project.service.impl.CocktailServiceImpl;
import by.silebin.final_project.service.impl.CommentServiceImpl;
import by.silebin.final_project.service.impl.IngredientServiceImpl;
import by.silebin.final_project.util.CocktailImageEncoder;
import by.silebin.final_project.validator.ParamValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class CocktailInfoCommand implements Command {

    private static final Logger logger = LogManager.getLogger(CocktailInfoCommand.class);

    private final CocktailService cocktailService = CocktailServiceImpl.getInstance();
    private final IngredientService ingredientService = IngredientServiceImpl.getInstance();
    private final CommentService commentService = CommentServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {

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

                List<CommentDto> comments = commentService.getCommentsForCocktail(id);
                request.setAttribute(RequestAttribute.COMMENTS, comments);

                return new Router(PagePath.COCKTAIL_PAGE, Router.RouterType.FORWARD);
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
