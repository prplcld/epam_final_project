package by.silebin.final_project.service.impl;

import by.silebin.final_project.dao.CocktailDao;
import by.silebin.final_project.dao.impl.CocktailDaoImpl;
import by.silebin.final_project.entity.Cocktail;
import by.silebin.final_project.exception.DaoException;
import by.silebin.final_project.exception.ServiceException;
import by.silebin.final_project.service.CocktailService;
import by.silebin.final_project.util.CocktailImageEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CocktailServiceImpl implements CocktailService {

    private static final Logger logger = LogManager.getLogger(CocktailServiceImpl.class);

    private static final CocktailServiceImpl instance = new CocktailServiceImpl();

    public static CocktailServiceImpl getInstance() {
        return instance;
    }

    private CocktailServiceImpl() {

    }

    @Override
    public List<Cocktail> getAllCocktails() throws ServiceException {
        CocktailDao cocktailDao = CocktailDaoImpl.getInstance();
        try {
            List<Cocktail> cocktails = cocktailDao.getAll();
            for (Cocktail c : cocktails) {
                CocktailImageEncoder.encodeImage(c);
            }
            return cocktails;
        } catch (IOException e) {
            logger.error(e);
            throw new ServiceException("Error during encoding image", e);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException("Can't handle CocktailServiceImpl.getAllCocktails", e);
        }
    }

    @Override
    public int insert(Cocktail cocktail) throws ServiceException {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getCocktailsAmount() throws ServiceException {
        CocktailDao cocktailDao = CocktailDaoImpl.getInstance();
        try {
            return cocktailDao.getCocktailsAmount();
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException("Can't handle CocktailServiceImpl.getCocktailsAmount", e);
        }
    }

    @Override
    public List<Cocktail> getLimited(int start, int amount) throws ServiceException {
        CocktailDao cocktailDao = CocktailDaoImpl.getInstance();
        try {
            List<Cocktail> cocktails = cocktailDao.getLimited(start, amount);
            for (Cocktail c : cocktails) {
                CocktailImageEncoder.encodeImage(c);
            }
            return cocktails;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException("Can't handle CocktailServiceImpl.getLimited", e);
        } catch (IOException e) {
            logger.error(e);
            throw new ServiceException("Error during encoding image", e);
        }
    }


    @Override
    public Optional<Cocktail> getById(int id) throws ServiceException {
        CocktailDao cocktailDao = CocktailDaoImpl.getInstance();
        try {
            return cocktailDao.findById(id);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException("Can't handle CocktailServiceImpl.getById", e);
        }
    }

    @Override
    public List<Cocktail> getByNameLike(String name) throws ServiceException {
        CocktailDao cocktailDao = CocktailDaoImpl.getInstance();
        try {
            List<Cocktail> cocktails = cocktailDao.getByNameLike(name);
            for (Cocktail c : cocktails) {
                CocktailImageEncoder.encodeImage(c);
            }
            return cocktails;
        } catch (DaoException | IOException e) {
            logger.error(e);
            throw new ServiceException("Can't handle CocktailServiceImpl.getByNameLike", e);
        }
    }

    @Override
    public List<Cocktail> getByUserId(int id) throws ServiceException {
        CocktailDao cocktailDao = CocktailDaoImpl.getInstance();
        try {
            List<Cocktail> cocktails = cocktailDao.getByUserId(id);
            for (Cocktail c : cocktails) {
                CocktailImageEncoder.encodeImage(c);
            }
            return cocktails;
        } catch (DaoException | IOException e) {
            logger.error(e);
            throw new ServiceException("Can't handle CocktailServiceImpl.getByUserId", e);
        }
    }

    @Override
    public List<Cocktail> getUnapprovedCocktails() throws ServiceException {
        CocktailDao cocktailDao = CocktailDaoImpl.getInstance();
        try {
            List<Cocktail> cocktails = cocktailDao.getUnapprovedCocktails();
            for (Cocktail c : cocktails) {
                CocktailImageEncoder.encodeImage(c);
            }
            return cocktails;
        } catch (DaoException | IOException e) {
            logger.error(e);
            throw new ServiceException("Can't handle CocktailServiceImpl.getUnapprovedCocktails", e);
        }
    }

    @Override
    public boolean approveCocktail(int cocktailId) throws ServiceException {
        CocktailDao cocktailDao = CocktailDaoImpl.getInstance();
        try {
            return cocktailDao.updateCocktailApproval(cocktailId);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException("Can't handle CocktailServiceImpl.approveCocktail", e);
        }
    }

    @Override
    public boolean deleteCocktail(int cocktailId) throws ServiceException {
        CocktailDao cocktailDao = CocktailDaoImpl.getInstance();
        try {
            return cocktailDao.delete(cocktailId);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException("Can't handle CocktailServiceImpl.deleteCocktail", e);
        }
    }

    @Override
    public int insertCocktailWithIngredients(Cocktail cocktail, List<Integer> ingredientIds, List<Integer> ingredientAmounts) throws ServiceException {
        if (ingredientIds.size() != ingredientAmounts.size()) {
            throw new ServiceException("size of lists do not match");
        }
        CocktailDao cocktailDao = CocktailDaoImpl.getInstance();
        try {
            return cocktailDao.insertCocktailWithIngredients(cocktail, ingredientIds, ingredientAmounts);
        } catch (DaoException | SQLException e) {
            throw new ServiceException();
        }
    }

    @Override
    public void updateCocktailWithIngredients(Cocktail cocktail, List<Integer> ingredientIds, List<Integer> ingredientAmounts) throws ServiceException {
        if (ingredientIds.size() != ingredientAmounts.size()) {
            throw new ServiceException("size of lists do not match");
        }
        CocktailDao cocktailDao = CocktailDaoImpl.getInstance();

        try {
            cocktailDao.updateCocktailWithIngredients(cocktail, ingredientIds, ingredientAmounts);
        } catch (DaoException | SQLException e) {
            throw new ServiceException();
        }
    }
}
