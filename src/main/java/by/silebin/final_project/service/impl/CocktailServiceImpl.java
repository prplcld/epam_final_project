package by.silebin.final_project.service.impl;

import by.silebin.final_project.entity.Cocktail;
import by.silebin.final_project.exception.DaoException;
import by.silebin.final_project.exception.ServiceException;
import by.silebin.final_project.dao.CocktailDao;
import by.silebin.final_project.dao.impl.CocktailDaoImpl;
import by.silebin.final_project.service.CocktailService;
import by.silebin.final_project.util.CocktailImageEncoder;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class CocktailServiceImpl implements CocktailService {

    @Override
    public List<Cocktail> getAllCocktails() throws ServiceException {
        CocktailDao cocktailDao = CocktailDaoImpl.getInstance();
        try {
            List<Cocktail> cocktails = cocktailDao.getAll();
            for(Cocktail c : cocktails) {
                CocktailImageEncoder.encodeImage(c);
            }
            return cocktails;
        } catch (IOException e) {
            throw  new ServiceException("Error during encoding image", e);
        } catch (DaoException e) {
            throw  new ServiceException("Can't handle get all cocktails at CocktailService", e);
        }
    }

    @Override
    public boolean insert(Cocktail cocktail) throws ServiceException {
        CocktailDao cocktailDao = CocktailDaoImpl.getInstance();
        try {
            boolean result = cocktailDao.insert(cocktail);
            return  result;
        } catch (DaoException e) {
            throw  new ServiceException("Can't handle insert at CocktailService", e);
        }
    }

    @Override
    public int getCocktailsAmount() throws ServiceException {
        CocktailDao cocktailDao = CocktailDaoImpl.getInstance();
        try {
            return cocktailDao.getCocktailsAmount();
        } catch (DaoException e) {
            throw new ServiceException("Can't handle get cocktails amount at CocktailService", e);
        }
    }

    @Override
    public List<Cocktail> getLimited(int start, int amount) throws ServiceException {
        CocktailDao cocktailDao = CocktailDaoImpl.getInstance();
        try {
            List<Cocktail> cocktails =  cocktailDao.getLimited(start, amount);
            for (Cocktail c : cocktails) {
                CocktailImageEncoder.encodeImage(c);
            }
            return cocktails;
        } catch (DaoException e) {
            throw new ServiceException("Can't handle get limited cocktails at CocktailService", e);
        } catch (IOException e) {
            throw new ServiceException("Error during encoding image", e);
        }
    }


    @Override
    public Optional<Cocktail> getById(int id) throws ServiceException {
        CocktailDao cocktailDao = CocktailDaoImpl.getInstance();
        try {
            return cocktailDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException("Can't handle get by id at CocktailService", e);
        }
    }
}