package by.silebin.final_project.model.service.impl;

import by.silebin.final_project.entity.Cocktail;
import by.silebin.final_project.model.dao.CocktailsDao;
import by.silebin.final_project.model.dao.impl.CocktailsDaoImpl;
import by.silebin.final_project.model.service.CocktailService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;

public class CocktailServiceImpl implements CocktailService {
    @Override
    public List<Cocktail> getAllCocktails() throws IOException{
        CocktailsDao cocktailsDao = CocktailsDaoImpl.getInstance();
        List<Cocktail> cocktails = cocktailsDao.getAll();
        for(Cocktail c : cocktails) {
            encodeImage(c);
        }
        return cocktails;
    }

    @Override
    public boolean insert(Cocktail cocktail) {
        CocktailsDao cocktailsDao = CocktailsDaoImpl.getInstance();
        return cocktailsDao.insert(cocktail);
    }

    private void encodeImage(Cocktail cocktail) throws IOException {
        InputStream inputStream = cocktail.getIcon();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead = -1;

        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        byte[] imageBytes = outputStream.toByteArray();

        cocktail.setBase64Icon(Base64.getEncoder().encodeToString(imageBytes));

        inputStream.close();
        outputStream.close();
    }
}
