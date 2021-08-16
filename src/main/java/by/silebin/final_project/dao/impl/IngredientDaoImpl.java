package by.silebin.final_project.dao.impl;

import by.silebin.final_project.dao.IngredientDao;
import by.silebin.final_project.entity.Ingredient;
import by.silebin.final_project.exception.DaoException;
import by.silebin.final_project.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.silebin.final_project.dao.ColumnName.*;

/**
 * Implementation of {@link IngredientDao}. Provides methods to interact with Ingredient data from database.
 * Methods connect to database using {@link Connection} from {@link ConnectionPool} and manipulate with data(save, edit, etc.).
 */
public class IngredientDaoImpl implements IngredientDao {

    private static final Logger logger = LogManager.getLogger(IngredientDaoImpl.class);

    /**
     * A single instance of the class (pattern Singleton)
     */
    private static final IngredientDaoImpl instance = new IngredientDaoImpl();
    /** An object of {@link ConnectionPool} */
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String GET_INGREDIENTS_BY_COCKTAIL_ID_SQL = "select name, amount, amount_scale from ingredients_in_cocktail c " +
            "join ingredients i on c.ingredient_id = i.id where cocktail_id = ?";
    private static final String INSERT_INGREDIENT_SQL = "insert into ingredients(name, amount_scale) values(?, ?)";
    private static final String UPDATE_INGREDIENT_SQL = "update ingredients set name = ?, amount_scale = ? where id = ?";
    private static final String DELETE_INGREDIENT_SQL = "delete from ingredients where id = ?";
    private static final String GET_ALL_INGREDIENTS = "select id, name, amount_scale from ingredients";
    private static final String INSERT_INGREDIENT_FOR_COCKTAIL = "insert into ingredients_in_cocktail(cocktail_id, ingredient_id, amount) values(?, ?, ?)";


    private IngredientDaoImpl() {
    }

    public static IngredientDaoImpl getInstance() {
        return instance;
    }

    /**
     * Connects to database and returns list of all ingredients.
     *
     * @return List of {@link Ingredient} with all ingredient's detailed data.
     * @throws DaoException when problems with database connection occurs.
     */
    @Override
    public List<Ingredient> getAll() throws DaoException {
        List<Ingredient> ingredients = new ArrayList<>();
        try(Connection connection = connectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_INGREDIENTS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Ingredient ingredient = new Ingredient();
                ingredient.setIngredientId(resultSet.getInt(ID));
                ingredient.setName(resultSet.getString(INGREDIENTS_NAME));
                ingredient.setAmountScale(resultSet.getString(INGREDIENTS_SCALE));
                ingredients.add(ingredient);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException("Can't handle IngredientDao.getAll request", e);
        }
        return ingredients;
    }

    /**
     * Connects to database and returns list of ingredients in cocktail.
     *
     * @param cocktailId is ID value of {@link Ingredient}.
     * @return List of {@link Ingredient} with ingredients for cocktail.
     * @throws DaoException when problems with database connection occurs.
     */
    @Override
    public List<Ingredient> getIngredientsByCocktailId(int cocktailId) throws DaoException {
        List<Ingredient> ingredients = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_INGREDIENTS_BY_COCKTAIL_ID_SQL)) {
            preparedStatement.setInt(1, cocktailId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Ingredient ingredient = new Ingredient();
                ingredient.setName(resultSet.getString(INGREDIENTS_NAME));
                ingredient.setAmount(resultSet.getInt(INGREDIENTS_AMOUNT));
                ingredient.setAmountScale(resultSet.getString(INGREDIENTS_SCALE));
                ingredients.add(ingredient);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException("Can't handle IngredientDao.getIngredientsByCocktailId request", e);
        }
        return ingredients;
    }

    /**
     * Connects to database and inserts ingredient.
     *
     * @param ingredient is {@link Ingredient} object that contains information for insert.
     * @throws DaoException when problems with database connection occurs.
     */
    @Override
    public boolean insert(Ingredient ingredient) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INGREDIENT_SQL)) {
            preparedStatement.setString(1, ingredient.getName());
            preparedStatement.setString(2, ingredient.getAmountScale());
            return !preparedStatement.execute();
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException("Can't handle IngredientDao.insert request", e);
        }
    }

    /**
     * Connects to database and updates ingredient.
     *
     * @param ingredient is {@link Ingredient} object that contains information for update.
     * @throws DaoException when problems with database connection occurs.
     */
    @Override
    public boolean update(Ingredient ingredient) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_INGREDIENT_SQL)) {
            preparedStatement.setString(1, ingredient.getName());
            preparedStatement.setString(2, ingredient.getAmountScale());
            preparedStatement.setInt(3, ingredient.getIngredientId());
            return !preparedStatement.execute();
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException("Can't handle IngredientDao.update request", e);
        }
    }

    /**
     * Connects to database and deletes ingredient.
     *
     * @param ingredientId is {@link Ingredient} ID value.
     * @throws DaoException when problems with database connection occurs.
     */
    @Override
    public boolean delete(int ingredientId) throws DaoException {
        try(Connection connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_INGREDIENT_SQL)) {
            preparedStatement.setInt(1, ingredientId);
            return !preparedStatement.execute();
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException("Can't handle IngredientDao.delete request", e);
        }
    }

    /**
     * Connects to database and adds ingredient for cocktail.
     *
     * @param cocktailId is cocktail ID value.
     * @param ingredientId is {@link Ingredient} ID value.
     * @param amount is amount of {@link Ingredient}.
     * @throws DaoException when problems with database connection occurs.
     */
    @Override
    public boolean insertIngredientForCocktail(int cocktailId, int ingredientId, int amount) throws DaoException {
        throw new UnsupportedOperationException("unsupported method");
    }
}
