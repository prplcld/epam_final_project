package by.silebin.final_project.model.service;

import by.silebin.final_project.entity.Cocktail;

import java.io.IOException;
import java.util.List;

public interface CocktailService {
    List<Cocktail> getAllCocktails() throws IOException;
    boolean insert(Cocktail cocktail);
}
