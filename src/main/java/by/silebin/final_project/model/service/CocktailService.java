package by.silebin.final_project.model.service;

import by.silebin.final_project.entity.Cocktail;
import by.silebin.final_project.exception.ServiceException;

import java.util.List;

public interface CocktailService {
    List<Cocktail> getAllCocktails() throws ServiceException;
    boolean insert(Cocktail cocktail) throws ServiceException;
    int getCocktailsAmount() throws ServiceException;
    List<Cocktail> getLimited(int start, int amount) throws ServiceException;
}
