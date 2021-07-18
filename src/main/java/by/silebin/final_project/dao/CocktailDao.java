package by.silebin.final_project.dao;

import by.silebin.final_project.entity.Cocktail;
import by.silebin.final_project.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface CocktailDao {
    Optional<Cocktail> findById(int id) throws DaoException;

    boolean update(Cocktail cocktail) throws DaoException;

    boolean delete(int id) throws DaoException;

    boolean insert(Cocktail cocktail) throws DaoException;

    List<Cocktail> getAll() throws DaoException;

    int getCocktailsAmount() throws DaoException;

    List<Cocktail> getLimited(int start, int amount) throws DaoException;
}
