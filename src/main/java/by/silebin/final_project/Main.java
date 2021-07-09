package by.silebin.final_project;

import by.silebin.final_project.entity.Cocktail;
import by.silebin.final_project.model.service.CocktailService;
import by.silebin.final_project.model.service.impl.CocktailServiceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        CocktailService cocktailService = new CocktailServiceImpl();
        Cocktail cocktail = new Cocktail();
        cocktail.setName("test");
        cocktail.setDescription("testestestestestestesters");
        cocktail.setUserId(2);
        InputStream inputStream = new FileInputStream(new File("E:\\prog\\java\\java_task_final_project\\src\\main\\webapp\\pages\\static\\image\\team2.png"));
        cocktail.setIcon(inputStream);
        cocktailService.insert(cocktail);
    }
}
