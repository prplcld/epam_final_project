package by.silebin.final_project.model.dao;

import by.silebin.final_project.entity.Cocktail;
import by.silebin.final_project.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface CocktailsDao {
    Optional<Cocktail> findById(int id);
    boolean update(Cocktail cocktail);
    boolean delete(Cocktail cocktail);
    boolean insert(Cocktail cocktail) throws DaoException;
    List<Cocktail> getAll() throws DaoException;
    int getCocktailsAmount() throws DaoException;
    List<Cocktail> getLimited(int start, int amount) throws DaoException;
}
