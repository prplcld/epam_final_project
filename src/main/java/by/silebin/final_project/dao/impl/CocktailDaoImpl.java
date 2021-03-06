package by.silebin.final_project.dao.impl;

import by.silebin.final_project.dao.CocktailDao;
import by.silebin.final_project.entity.Cocktail;
import by.silebin.final_project.exception.DaoException;
import by.silebin.final_project.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.silebin.final_project.dao.ColumnName.*;

/**
 * Implementation of {@link CocktailDao}. Provides methods to interact with Cocktail data from database.
 * Methods connect to database using {@link Connection} from {@link ConnectionPool} and manipulate with data(save, edit, etc.).
 */
public class CocktailDaoImpl implements CocktailDao {

    private static final Logger logger = LogManager.getLogger(CocktailDaoImpl.class);

    /**
     * A single instance of the class (pattern Singleton)
     */
    private static final CocktailDaoImpl instance = new CocktailDaoImpl();

    /**
     * An object of {@link ConnectionPool}
     */
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String GET_COCKTAIL_BY_ID_SQL = "select id, name, description, icon, user_id from cocktails where id = ?";
    private static final String GET_ALL_COCKTAILS_SQL = "select id, name, description, icon, user_id from cocktails where approved = true";
    private static final String INSERT_COCKTAIL_SQL = "insert into cocktails(name, description, icon, user_id, approved) values(?, ?, ?, ?, ?)";
    private static final String COUNT_COCKTAILS_SQL = "select count(*) from cocktails";
    private static final String GET_LIMIT_COCKTAILS_SQL = "select id, name, description, icon, user_id from cocktails where approved = true limit ?, ?";
    private static final String UPDATE_COCKTAIL_SQL = "update cocktails set name = ?, description = ?, icon = ? where id = ?";
    private static final String DELETE_COCKTAIL_SQL = "delete from cocktails where id = ?";
    private static final String GET_COCKTAILS_LIKE = "select id, name, description, icon, user_id from cocktails where name like ? and approved = true";
    private static final String GET_COCKTAILS_BY_USER_ID = "select id, name, description, icon, user_id from cocktails where user_id = ? and approved = true";
    private static final String GET_UNAPPROVED_COCKTAILS = "select id, name, description, icon, user_id from cocktails where approved = false";
    private static final String UPDATE_COCKTAIL_APPROVAL = "update cocktails set approved = true where id = ?";
    private static final String INSERT_INGREDIENT_FOR_COCKTAIL = "insert into ingredients_in_cocktail(cocktail_id, ingredient_id, amount) values(?, ?, ?)";
    private static final String DELETE_INGREDIENTS_FROM_COCKTAIL = "delete from ingredients_in_cocktail where cocktail_id = ?";

    /**
     * Private constructor without parameters
     */
    private CocktailDaoImpl() {
    }

    /**
     * Returns the instance of the class
     *
     * @return Object of {@link CocktailDaoImpl}
     */
    public static CocktailDaoImpl getInstance() {
        return instance;
    }

    /**
     * Connects to database and returns all info about cocktail by ID.
     *
     * @param id is cocktail ID value.
     * @return {@link Optional<Cocktail>}.
     * @throws DaoException when problems with database connection occurs.
     */
    @Override
    public Optional<Cocktail> findById(int id) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_COCKTAIL_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Cocktail cocktail = new Cocktail();
                cocktail.setCocktailId(resultSet.getInt(ID));
                cocktail.setName(resultSet.getString(COCKTAILS_NAME));
                cocktail.setDescription(resultSet.getString(COCKTAILS_DESCRIPTION));
                cocktail.setIcon(resultSet.getBlob(COCKTAILS_ICON).getBinaryStream());
                cocktail.setUserId(resultSet.getInt(COCKTAILS_USER_ID));
                return Optional.of(cocktail);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException("Can't handle CocktailDao.findById", e);
        }
        return Optional.empty();
    }

    /**
     * Connects to database and updates cocktail.
     *
     * @param cocktail is {@link Cocktail} object that contains all info about cocktail for update.
     * @throws DaoException when problems with database connection occurs.
     */
    @Override
    public boolean update(Cocktail cocktail) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COCKTAIL_SQL)) {
            preparedStatement.setString(1, cocktail.getName());
            preparedStatement.setString(2, cocktail.getDescription());
            preparedStatement.setBinaryStream(3, cocktail.getIcon());
            preparedStatement.setInt(4, cocktail.getCocktailId());
            return !preparedStatement.execute();
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException("Can't handle CocktailDao.update request", e);
        }
    }

    /**
     * Connects to database and deletes cocktail.
     *
     * @param id is cocktail ID value.
     * @throws DaoException when problems with database connection occurs.
     */
    @Override
    public boolean delete(int id) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_COCKTAIL_SQL)) {
            preparedStatement.setInt(1, id);
            return !preparedStatement.execute();
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException("Can't handle CocktailDao.delete request", e);
        }
    }

    /**
     * Connects to database and inserts cocktail.
     *
     * @param cocktail is {@link Cocktail} object that contains all info about cocktail for insert.
     * @throws DaoException when problems with database connection occurs.
     */
    @Override
    public int insert(Cocktail cocktail) throws DaoException {
        throw new UnsupportedOperationException("unsupported method");
    }

    /**
     * Connects to database and returns list of all cocktails.
     *
     * @return List of {@link Cocktail} with all cocktail's detailed data.
     * @throws DaoException when problems with database connection occurs.
     */
    @Override
    public List<Cocktail> getAll() throws DaoException {
        List<Cocktail> cocktails = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_COCKTAILS_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            buildListFromResultSet(resultSet, cocktails);
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException("Can't handle CocktailDao.getAll request", e);
        }
        return cocktails;
    }

    /**
     * Connects to database and returns cocktails amount.
     *
     * @return amount of cocktails.
     * @throws DaoException when problems with database connection occurs.
     */
    @Override
    public int getCocktailsAmount() throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(COUNT_COCKTAILS_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new DaoException();
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException("Can't handle CocktailDao.getCocktailsAmount request", e);
        }
    }

    /**
     * Connects to database and returns limited list of cocktails.
     *
     * @param start  is start of limit.
     * @param amount is amount of cocktails to get.
     * @return List of {@link Cocktail} with limited cocktail's detailed data.
     * @throws DaoException when problems with database connection occurs.
     */
    @Override
    public List<Cocktail> getLimited(int start, int amount) throws DaoException {
        List<Cocktail> cocktails = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_LIMIT_COCKTAILS_SQL)) {
            preparedStatement.setInt(1, start);
            preparedStatement.setInt(2, amount);
            ResultSet resultSet = preparedStatement.executeQuery();
            buildListFromResultSet(resultSet, cocktails);
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException("Can't handle CocktailDao.getLimited request", e);
        }
        return cocktails;
    }

    /**
     * Connects to database and returns list of cocktails by name.
     *
     * @param name is name of the cocktail to search for
     * @return List of {@link Cocktail} with cocktails having searched name.
     * @throws DaoException when problems with database connection occurs.
     */
    @Override
    public List<Cocktail> getByNameLike(String name) throws DaoException {
        List<Cocktail> cocktails = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_COCKTAILS_LIKE)) {
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            buildListFromResultSet(resultSet, cocktails);
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException("Can't handle CocktailDao.getByNameLike request", e);
        }
        return cocktails;
    }

    /**
     * Connects to database and returns list of all cocktails of a particular user.
     *
     * @param userId is ID value of user that created cocktails;
     * @return List of {@link Cocktail} with cocktail's detailed data created by one user.
     * @throws DaoException when problems with database connection occurs.
     */
    @Override
    public List<Cocktail> getByUserId(int userId) throws DaoException {
        List<Cocktail> cocktails = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_COCKTAILS_BY_USER_ID)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            buildListFromResultSet(resultSet, cocktails);
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException("Can't handle CocktailDao.getByUserId request", e);
        }
        return cocktails;
    }

    /**
     * Connects to database and returns list of unapproved cocktails.
     *
     * @return List of {@link Cocktail} with unapproved cocktail's detailed data.
     * @throws DaoException when problems with database connection occurs.
     */
    @Override
    public List<Cocktail> getUnapprovedCocktails() throws DaoException {
        List<Cocktail> cocktails = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_UNAPPROVED_COCKTAILS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            buildListFromResultSet(resultSet, cocktails);
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException("Can't handle CocktailDao.getUnapprovedCocktails request", e);
        }
        return cocktails;
    }

    /**
     * Connects to database and returns list of all cocktails.
     *
     * @param cocktailId is {@link Cocktail} ID value.
     * @throws DaoException when problems with database connection occurs.
     */
    @Override
    public boolean updateCocktailApproval(int cocktailId) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COCKTAIL_APPROVAL)) {
            preparedStatement.setInt(1, cocktailId);
            return !preparedStatement.execute();
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException("Can't handle CocktailDao. updateCocktailApproval request", e);
        }
    }

    /**
     * Connects to database and returns list of all cocktails.
     *
     * @param cocktail          is {@link Cocktail} object that contains all info about cocktail for insert.
     * @param ingredientAmounts list of ingredient amounts.
     * @param ingredientIds     list of ingredient IDs.
     * @return insert ID of cocktail.
     * @throws DaoException when problems with database connection occurs.
     */
    @Override
    public int insertCocktailWithIngredients(Cocktail cocktail, List<Integer> ingredientIds, List<Integer> ingredientAmounts) throws DaoException, SQLException {
        PreparedStatement preparedStatement = null;
        Connection connection = connectionPool.getConnection();
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(INSERT_COCKTAIL_SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, cocktail.getName());
            preparedStatement.setString(2, cocktail.getDescription());
            preparedStatement.setBinaryStream(3, cocktail.getIcon());
            preparedStatement.setInt(4, cocktail.getUserId());
            preparedStatement.setBoolean(5, cocktail.isApproved());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            int insertId;
            if (resultSet.next()) {
                insertId = resultSet.getInt(1);
            } else {
                connection.rollback();
                throw new DaoException("could not get insert cocktail id");
            }

            for (int i = 0; i < ingredientIds.size(); i++) {
                preparedStatement = connection.prepareStatement(INSERT_INGREDIENT_FOR_COCKTAIL);
                preparedStatement.setInt(1, insertId);
                preparedStatement.setInt(2, ingredientIds.get(i));
                preparedStatement.setInt(3, ingredientAmounts.get(i));
                preparedStatement.execute();
            }

            connection.commit();
            return insertId;
        } catch (SQLException e) {
            connection.rollback();
            logger.error(e);
            throw new DaoException("Can't handle CocktailDao.insertCocktailWithIngredients request", e);
        } finally {
            connection.setAutoCommit(true);
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            connection.close();
        }
    }

    /**
     * Connects to database and returns list of all cocktails.
     *
     * @param cocktail          is {@link Cocktail} object that contains all info about cocktail for update.
     * @param ingredientAmounts list of ingredient amounts.
     * @param ingredientIds     list of ingredient IDs.
     * @throws DaoException when problems with database connection occurs.
     */
    @Override
    public void updateCocktailWithIngredients(Cocktail cocktail, List<Integer> ingredientIds, List<Integer> ingredientAmounts) throws DaoException, SQLException {
        PreparedStatement preparedStatement = null;
        Connection connection = connectionPool.getConnection();
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(UPDATE_COCKTAIL_SQL);
            preparedStatement.setString(1, cocktail.getName());
            preparedStatement.setString(2, cocktail.getDescription());
            preparedStatement.setBinaryStream(3, cocktail.getIcon());
            preparedStatement.setInt(4, cocktail.getCocktailId());
            preparedStatement.execute();

            preparedStatement = connection.prepareStatement(DELETE_INGREDIENTS_FROM_COCKTAIL);
            preparedStatement.setInt(1, cocktail.getCocktailId());
            preparedStatement.execute();

            for (int i = 0; i < ingredientIds.size(); i++) {
                preparedStatement = connection.prepareStatement(INSERT_INGREDIENT_FOR_COCKTAIL);
                preparedStatement.setInt(1, cocktail.getCocktailId());
                preparedStatement.setInt(2, ingredientIds.get(i));
                preparedStatement.setInt(3, ingredientAmounts.get(i));
                preparedStatement.execute();
            }

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            logger.error(e);
            throw new DaoException("Can't handle CocktailDao.updateCocktailWithIngredients request", e);
        } finally {
            connection.setAutoCommit(true);
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            connection.close();
        }
    }

    private void buildListFromResultSet(ResultSet resultSet, List<Cocktail> cocktails) throws SQLException {
        while (resultSet.next()) {
            Cocktail cocktail = new Cocktail();
            cocktail.setCocktailId(resultSet.getInt(ID));
            cocktail.setName(resultSet.getString(COCKTAILS_NAME));
            cocktail.setDescription(resultSet.getString(COCKTAILS_DESCRIPTION));
            cocktail.setUserId(resultSet.getInt(COCKTAILS_USER_ID));
            cocktail.setIcon(resultSet.getBlob(COCKTAILS_ICON).getBinaryStream());
            cocktails.add(cocktail);
        }
    }
}
