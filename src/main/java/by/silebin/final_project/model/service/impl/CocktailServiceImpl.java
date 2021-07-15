package by.silebin.final_project.model.service.impl;

import by.silebin.final_project.entity.Cocktail;
import by.silebin.final_project.exception.DaoException;
import by.silebin.final_project.exception.ServiceException;
import by.silebin.final_project.model.dao.CocktailsDao;
import by.silebin.final_project.model.dao.impl.CocktailsDaoImpl;
import by.silebin.final_project.model.service.CocktailService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class CocktailServiceImpl implements CocktailService {

    @Override
    public List<Cocktail> getAllCocktails() throws ServiceException {
        CocktailsDao cocktailsDao = CocktailsDaoImpl.getInstance();
        List<Cocktail> cocktails = new ArrayList<>();
        try {
            cocktails = cocktailsDao.getAll();
            for(Cocktail c : cocktails) {
                encodeImage(c);
            }
            return cocktails;
        } catch (IOException e) {
            throw  new ServiceException();
        } catch (DaoException e) {
            throw  new ServiceException();
        }
    }

    @Override
    public boolean insert(Cocktail cocktail) throws ServiceException {
        CocktailsDao cocktailsDao = CocktailsDaoImpl.getInstance();
        try {
            boolean result = cocktailsDao.insert(cocktail);
            return  result;
        } catch (DaoException e) {
            throw  new ServiceException();
        }
    }



    @Override
    public int getCocktailsAmount() throws ServiceException {
        CocktailsDao cocktailsDao = CocktailsDaoImpl.getInstance();
        try {
            return cocktailsDao.getCocktailsAmount();
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    @Override
    public List<Cocktail> getLimited(int start, int amount) throws ServiceException {
        CocktailsDao cocktailsDao = CocktailsDaoImpl.getInstance();
        try {
            List<Cocktail> cocktails =  cocktailsDao.getLimited(start, amount);
            for (Cocktail c : cocktails) {
                encodeImage(c);
            }
            return cocktails;
        } catch (DaoException e) {
            throw new ServiceException();
        } catch (IOException e) {
            throw new ServiceException();
        }
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
