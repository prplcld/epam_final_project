package by.silebin.final_project.model.dao;

import by.silebin.final_project.entity.Ingredient;

import java.util.Optional;
import java.util.List;

public interface IngredientsDao {
    Optional<Ingredient> findById(int id);
    boolean update(Ingredient ingredient);
    boolean delete(Ingredient ingredient);
    boolean insert(Ingredient ingredient);
    List<Ingredient> getAll();
    List<Ingredient> getIngredientsForCocktail(int cocktailId);
}
