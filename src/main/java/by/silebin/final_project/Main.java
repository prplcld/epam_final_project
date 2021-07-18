package by.silebin.final_project;

import by.silebin.final_project.entity.Cocktail;
import by.silebin.final_project.exception.DaoException;
import by.silebin.final_project.exception.ServiceException;
import by.silebin.final_project.service.CocktailService;
import by.silebin.final_project.service.impl.CocktailServiceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, ServiceException, DaoException {
        CocktailService cocktailService = new CocktailServiceImpl();
        Cocktail cocktail = new Cocktail();
        cocktail.setName("test9");
        cocktail.setDescription("testestestestestestesters");
        cocktail.setUserId(3);
        InputStream inputStream = new FileInputStream(new File("D:\\prog\\java\\java_task_final_project\\src\\main\\webapp\\pages\\static\\image\\cocktail.png"));
        cocktail.setIcon(inputStream);
        cocktailService.insert(cocktail);
//        UserDao userDao = UserDaoImpl.getInstance();
//        userDao.register("test", "test");
    }
}
