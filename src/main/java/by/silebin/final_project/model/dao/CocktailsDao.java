package by.silebin.final_project.model.dao;

import by.silebin.final_project.entity.Cocktail;
import java.util.List;
import java.util.Optional;

public interface CocktailsDao {
    Optional<Cocktail> findById(int id);
    boolean update(Cocktail cocktail);
    boolean delete(Cocktail cocktail);
    boolean insert(Cocktail cocktail);
    List<Cocktail> getAll();
}
