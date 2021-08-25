package by.silebin.final_project.service;

import by.silebin.final_project.entity.Ingredient;
import by.silebin.final_project.exception.ServiceException;

import java.util.List;

public interface IngredientService {
    /**
     *
     * @param cocktailId cocktail ID value
     * @return {@link List<Ingredient>} list of ingredients for cocktail
     * @throws ServiceException
     */
    List<Ingredient> getIngredientsForCocktail(int cocktailId) throws ServiceException;

    /**
     *
     * @return {@link List<Ingredient>} list of all ingredients
     * @throws ServiceException
     */
    List<Ingredient> getIngredients() throws ServiceException;

    /**
     *
     * @param ingredientIds list of ingredient ID values
     * @param ingredientAmounts list of ingredient amounts
     * @param cocktailId cocktail ID value
     * @throws ServiceException
     */
    void addIngredientsForCocktail(List<Integer> ingredientIds, List<Integer> ingredientAmounts, int cocktailId) throws ServiceException;
}
