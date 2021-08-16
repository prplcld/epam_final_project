package by.silebin.final_project.service.impl;

import by.silebin.final_project.dao.IngredientDao;
import by.silebin.final_project.dao.impl.IngredientDaoImpl;
import by.silebin.final_project.entity.Ingredient;
import by.silebin.final_project.exception.DaoException;
import by.silebin.final_project.exception.ServiceException;
import by.silebin.final_project.service.IngredientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class IngredientServiceImpl implements IngredientService {

    private static final Logger logger = LogManager.getLogger(IngredientServiceImpl.class);


    private static final IngredientServiceImpl instance = new IngredientServiceImpl();

    public static IngredientServiceImpl getInstance() {
        return instance;
    }

    private IngredientServiceImpl() {

    }


    @Override
    public List<Ingredient> getIngredientsForCocktail(int cocktailId) throws ServiceException {
        IngredientDao ingredientDao = IngredientDaoImpl.getInstance();

        try {
            return ingredientDao.getIngredientsByCocktailId(cocktailId);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException("Can't handle IngredientServiceImpl.getIngredientsForCocktail", e);
        }
    }

    @Override
    public List<Ingredient> getIngredients() throws ServiceException {
        try {
            IngredientDao ingredientDao = IngredientDaoImpl.getInstance();
            return ingredientDao.getAll();
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException("Can't handle IngredientServiceImpl.getIngredients", e);
        }
    }

    @Override
    public void addIngredientsForCocktail(List<Integer> ingredientIds, List<Integer> ingredientAmounts, int cocktailId) throws ServiceException {
        throw new UnsupportedOperationException();
    }
}
