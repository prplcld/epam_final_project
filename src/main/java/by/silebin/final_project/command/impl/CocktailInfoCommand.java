package by.silebin.final_project.command.impl;

import by.silebin.final_project.command.Command;
import by.silebin.final_project.command.PagePath;
import by.silebin.final_project.command.Router;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class CocktailInfoCommand implements Command {

    private static final Logger logger = LogManager.getLogger(CocktailInfoCommand.class);

    CocktailService cocktailService = new CocktailServiceImpl();
    IngredientService ingredientService = new IngredientServiceImpl();
    CommentService commentService = new CommentServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            Optional<Cocktail> cocktailOptional = cocktailService.getById(id);
            if (cocktailOptional.isPresent()) {
                Cocktail cocktail = cocktailOptional.get();
                CocktailImageEncoder.encodeImage(cocktail);
                request.setAttribute("cocktail", cocktail);
                List<Ingredient> ingredients = ingredientService.getIngredientsForCocktail(cocktail.getCocktailId());
                request.setAttribute("ingredients", ingredients);

                List<CommentDto> comments  = commentService.getCommentsForCocktail(id);
                request.setAttribute("comments", comments);

                router = new Router(PagePath.COCKTAIL_PAGE, Router.RouterType.FORWARD);
            }
            else {
                logger.error("cocktail not found");
                request.setAttribute("message", "cocktail not found");
                router = new Router(PagePath.NOT_FOUND_PAGE, Router.RouterType.FORWARD);
            }
        } catch (ServiceException | IOException e) {
            logger.error(e);
            request.setAttribute("exception", e);
            router =  new Router(PagePath.ERROR_PAGE, Router.RouterType.FORWARD);
        }
        request.getSession().setAttribute("cocktailId", id);
        return router;
    }
}
