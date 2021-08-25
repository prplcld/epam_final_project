package by.silebin.final_project.service;

import by.silebin.final_project.entity.Cocktail;
import by.silebin.final_project.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface CocktailService {

    /**
     *
     * @return {@link List<Cocktail>} list of all cocktails
     * @throws ServiceException
     */
    List<Cocktail> getAllCocktails() throws ServiceException;

    /**
     *
     * @param cocktail is {@Cocktail} object containing data
     * @return insert id of cocktail
     * @throws ServiceException
     */
    int insert(Cocktail cocktail) throws ServiceException;

    /**
     *
     * @return amount of cocktails
     * @throws ServiceException
     */
    int getCocktailsAmount() throws ServiceException;

    /**
     *
     * @param start start of limit
     * @param amount amount of cocktials to get
     * @return {@link List<Cocktail>} limited list of cocktails
     * @throws ServiceException
     */
    List<Cocktail> getLimited(int start, int amount) throws ServiceException;

    /**
     *
     * @param id is ID value of {@link Cocktail}
     * @return {@link Optional<Cocktail>} optional of cocktail
     * @throws ServiceException
     */
    Optional<Cocktail> getById(int id) throws ServiceException;

    /**
     *
     * @param name is name of cocktail to search for
     * @return {@link List<Cocktail>} list of searched cocktails
     * @throws ServiceException
     */
    List<Cocktail> getByNameLike(String name) throws ServiceException;

    /**
     *
     * @param id is ID value of user that created cocktail
     * @return {@link List<Cocktail>} list of cocktails that where created by particular user
     * @throws ServiceException
     */
    List<Cocktail> getByUserId(int id) throws ServiceException;

    /**
     *
     * @return {@link List<Cocktail>} list of cocktails that are unapproved
     * @throws ServiceException
     */
    List<Cocktail> getUnapprovedCocktails() throws ServiceException;

    /**
     *
     * @param cocktailId is {@link Cocktail} ID value
     * @return success of the operation
     * @throws ServiceException
     */
    boolean approveCocktail(int cocktailId) throws ServiceException;

    /**
     *
     * @param cocktailId is {@link Cocktail} ID value
     * @return success of the operation
     * @throws ServiceException
     */
    boolean deleteCocktail(int cocktailId) throws ServiceException;

    /**
     *
     * @param cocktail {@link Cocktail} object that contains cocktail info
     * @param ingredientIds list of ingredient ID values
     * @param ingredientAmounts list of ingredient amounts
     * @return insert id of the cocktail
     * @throws ServiceException
     */
    int insertCocktailWithIngredients(Cocktail cocktail, List<Integer> ingredientIds, List<Integer> ingredientAmounts) throws ServiceException;

    /**
     *
     * @param cocktail {@link Cocktail} object that contains cocktail info
     * @param ingredientIds list of ingredient ID values
     * @param ingredientAmounts list of ingredient amounts
     * @throws ServiceException
     */
    void updateCocktailWithIngredients(Cocktail cocktail, List<Integer> ingredientIds, List<Integer> ingredientAmounts) throws ServiceException;
}
