package by.silebin.final_project.service;

import by.silebin.final_project.entity.Ingredient;
import by.silebin.final_project.exception.ServiceException;

import java.util.List;

public interface IngredientService {
    List<Ingredient> getIngredientsForCocktail(int cocktailId) throws ServiceException;
    List<Ingredient> getIngredients() throws ServiceException;
    void addIngredientsForCocktail(List<Integer> ingredientIds, List<Integer> ingredientAmounts, int cocktailId) throws ServiceException;
}
