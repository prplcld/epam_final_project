package by.silebin.final_project.dao;

import by.silebin.final_project.entity.Ingredient;
import by.silebin.final_project.exception.DaoException;

import java.util.List;

public interface IngredientDao {
    List<Ingredient> getAll() throws DaoException;

    List<Ingredient> getIngredientsForCocktail(int cocktailId) throws DaoException;

    boolean insert(Ingredient ingredient) throws DaoException;

    boolean update(Ingredient ingredient) throws DaoException;

    boolean delete(int ingredientId) throws DaoException;

}
