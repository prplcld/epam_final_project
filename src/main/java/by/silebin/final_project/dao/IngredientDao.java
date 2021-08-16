package by.silebin.final_project.dao;

import by.silebin.final_project.entity.Ingredient;
import by.silebin.final_project.exception.DaoException;

import java.util.List;

/**
 * Interface provides methods to interact with Ingredient data from database.
 * Methods should connect to database and manipulate with data(save, edit, etc.).
 */
public interface IngredientDao {

    /**
     * Connects to database and returns list of all ingredients.
     *
     * @return List of {@link Ingredient} with all ingredient's detailed data.
     * @throws DaoException when problems with database connection occurs.
     */
    List<Ingredient> getAll() throws DaoException;

    /**
     * Connects to database and returns list of ingredients in cocktail.
     *
     * @param cocktailId is ID value of {@link Ingredient}.
     * @return List of {@link Ingredient} with ingredients for cocktail.
     * @throws DaoException when problems with database connection occurs.
     */
    List<Ingredient> getIngredientsByCocktailId(int cocktailId) throws DaoException;

    /**
     * Connects to database and inserts ingredient.
     *
     * @param ingredient is {@link Ingredient} object that contains information for insert.
     * @throws DaoException when problems with database connection occurs.
     */
    boolean insert(Ingredient ingredient) throws DaoException;

    /**
     * Connects to database and updates ingredient.
     *
     * @param ingredient is {@link Ingredient} object that contains information for update.
     * @throws DaoException when problems with database connection occurs.
     */
    boolean update(Ingredient ingredient) throws DaoException;

    /**
     * Connects to database and deletes ingredient.
     *
     * @param ingredientId is {@link Ingredient} ID value.
     * @throws DaoException when problems with database connection occurs.
     */
    boolean delete(int ingredientId) throws DaoException;

    /**
     * Connects to database and adds ingredient for cocktail.
     *
     * @param cocktailId is cocktail ID value.
     * @param ingredientId is {@link Ingredient} ID value.
     * @param amount is amount of {@link Ingredient}.
     * @throws DaoException when problems with database connection occurs.
     */
    boolean insertIngredientForCocktail(int cocktailId, int ingredientId, int amount) throws DaoException;
}
