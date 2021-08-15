package by.silebin.final_project.service;

import by.silebin.final_project.entity.Cocktail;
import by.silebin.final_project.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface CocktailService {
    List<Cocktail> getAllCocktails() throws ServiceException;

    int insert(Cocktail cocktail) throws ServiceException;

    int getCocktailsAmount() throws ServiceException;

    List<Cocktail> getLimited(int start, int amount) throws ServiceException;

    Optional<Cocktail> getById(int id) throws ServiceException;

    List<Cocktail> getByNameLike(String name) throws ServiceException;

    List<Cocktail> getByUserId(int id) throws ServiceException;

    List<Cocktail> getUnapprovedCocktails() throws ServiceException;

    boolean approveCocktail(int cocktailId) throws ServiceException;

    boolean deleteCocktail(int cocktailId) throws ServiceException;

    int insertCocktailWithIngredients(Cocktail cocktail, List<Integer> ingredientIds, List<Integer> ingredientAmounts) throws ServiceException;

    void updateCocktailWithIngredients(Cocktail cocktail, List<Integer> ingredientIds, List<Integer> ingredientAmounts) throws ServiceException;
}
