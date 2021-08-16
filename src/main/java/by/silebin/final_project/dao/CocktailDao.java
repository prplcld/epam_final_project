package by.silebin.final_project.dao;

import by.silebin.final_project.entity.Cocktail;
import by.silebin.final_project.exception.DaoException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


/**
 * Interface provides methods to interact with Cocktail data from database.
 * Methods should connect to database and manipulate with data(save, edit, etc.).
 */
public interface CocktailDao {

    /**
     * Connects to database and returns all info about cocktail by ID.
     *
     * @param id is cocktail ID value.
     * @return {@link Optional<Cocktail>}.
     * @throws DaoException when problems with database connection occurs.
     */
    Optional<Cocktail> findById(int id) throws DaoException;

    /**
     * Connects to database and updates cocktail.
     *
     * @param cocktail is {@link Cocktail} object that contains all info about cocktail for update.
     * @throws DaoException when problems with database connection occurs.
     */
    boolean update(Cocktail cocktail) throws DaoException;

    /**
     * Connects to database and deletes cocktail.
     *
     * @param id is cocktail ID value.
     * @throws DaoException when problems with database connection occurs.
     */
    boolean delete(int id) throws DaoException;

    /**
     * Connects to database and inserts cocktail.
     *
     * @param cocktail is {@link Cocktail} object that contains all info about cocktail for insert.
     * @throws DaoException when problems with database connection occurs.
     */
    int insert(Cocktail cocktail) throws DaoException;

    /**
     * Connects to database and returns list of all cocktails.
     *
     * @return List of {@link Cocktail} with all cocktail's detailed data.
     * @throws DaoException when problems with database connection occurs.
     */
    List<Cocktail> getAll() throws DaoException;

    /**
     * Connects to database and returns cocktails amount.
     *
     * @return amount of cocktails.
     * @throws DaoException when problems with database connection occurs.
     */
    int getCocktailsAmount() throws DaoException;

    /**
     * Connects to database and returns limited list of cocktails.
     *
     * @param start is start of limit.
     * @param amount is amount of cocktails to get.
     * @return List of {@link Cocktail} with limited cocktail's detailed data.
     * @throws DaoException when problems with database connection occurs.
     */
    List<Cocktail> getLimited(int start, int amount) throws DaoException;

    /**
     * Connects to database and returns list of cocktails by name.
     *
     * @param name is name of the cocktail to search for
     * @return List of {@link Cocktail} with cocktails having searched name.
     * @throws DaoException when problems with database connection occurs.
     */
    List<Cocktail> getByNameLike(String name) throws DaoException;

    /**
     * Connects to database and returns list of all cocktails of a particular user.
     *
     * @param userId is ID value of user that created cocktails;
     * @return List of {@link Cocktail} with cocktail's detailed data created by one user.
     * @throws DaoException when problems with database connection occurs.
     */
    List<Cocktail> getByUserId(int userId) throws DaoException;

    /**
     * Connects to database and returns list of unapproved cocktails.
     *
     * @return List of {@link Cocktail} with unapproved cocktail's detailed data.
     * @throws DaoException when problems with database connection occurs.
     */
    List<Cocktail> getUnapprovedCocktails() throws DaoException;

    /**
     * Connects to database and returns list of all cocktails.
     *
     * @param cocktailId is {@link Cocktail} ID value.
     * @throws DaoException when problems with database connection occurs.
     */
    boolean updateCocktailApproval(int cocktailId) throws DaoException;

    /**
     * Connects to database and returns list of all cocktails.
     *
     * @param cocktail is {@link Cocktail} object that contains all info about cocktail for insert.
     * @param ingredientAmounts list of ingredient amounts.
     * @param ingredientIds list of ingredient IDs.
     * @return insert ID of cocktail.
     * @throws DaoException when problems with database connection occurs.
     */
    int insertCocktailWithIngredients(Cocktail cocktail, List<Integer> ingredientIds, List<Integer> ingredientAmounts) throws DaoException, SQLException;

    /**
     * Connects to database and returns list of all cocktails.
     *
     * @param cocktail is {@link Cocktail} object that contains all info about cocktail for update.
     * @param ingredientAmounts list of ingredient amounts.
     * @param ingredientIds list of ingredient IDs.
     * @throws DaoException when problems with database connection occurs.
     */
    void updateCocktailWithIngredients(Cocktail cocktail, List<Integer> ingredientIds, List<Integer> ingredientAmounts) throws DaoException, SQLException;
}
