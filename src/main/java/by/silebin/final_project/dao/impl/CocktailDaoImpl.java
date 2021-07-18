package by.silebin.final_project.dao.impl;

import by.silebin.final_project.entity.Cocktail;
import by.silebin.final_project.exception.DaoException;
import by.silebin.final_project.dao.CocktailDao;
import by.silebin.final_project.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static by.silebin.final_project.dao.ColumnName.*;


public class CocktailDaoImpl implements CocktailDao {

    private static final CocktailDaoImpl instance = new CocktailDaoImpl();
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String GET_COCKTAIL_BY_ID_SQL = "select id, name, description, icon, user_id from cocktails where id = ?";
    private static final String GET_ALL_COCKTAILS_SQL = "select id, name, description, icon, user_id from cocktails";
    private static final String INSERT_COCKTAIL_SQL = "insert into cocktails(name, description, icon, user_id) values(?, ?, ?, ?)";
    private static final String COUNT_COCKTAILS_SQL = "select count(*) from cocktails";
    private static final String GET_LIMIT_COCKTAILS_SQL = "select id, name, description, icon, user_id from cocktails limit ?, ?";
    private static final String UPDATE_COCKTAIL_SQL = "update cocktails set name = ?, description = ?, icon = ?";
    private static final String DELETE_COCKTAIL_SQL = "delete from cocktails where id = ?";

    private CocktailDaoImpl() {}

    public static CocktailDaoImpl getInstance() {
        return instance;
    }

    @Override
    public Optional<Cocktail> findById(int id) throws DaoException {
        try(Connection connection = connectionPool.getConnection();
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
            throw new DaoException("Can't handle find by id request", e);
        }
        return Optional.empty();
    }

    @Override
    public boolean update(Cocktail cocktail) throws DaoException {
        try(Connection connection = connectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COCKTAIL_SQL)) {
        preparedStatement.setString(1, cocktail.getName());
        preparedStatement.setString(2, cocktail.getDescription());
        preparedStatement.setBinaryStream(3, cocktail.getIcon());
        return !preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException("Can't handle update request", e);
        }
    }

    @Override
    public boolean delete(int id) throws DaoException {
        try(Connection connection = connectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_COCKTAIL_SQL)) {
            preparedStatement.setInt(1, id);
            return !preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException("Can't handle delete request", e);
        }
    }

    @Override
    public boolean insert(Cocktail cocktail) throws DaoException {
        try(Connection connection = connectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COCKTAIL_SQL)) {
            preparedStatement.setString(1, cocktail.getName());
            preparedStatement.setString(2, cocktail.getDescription());
            preparedStatement.setBinaryStream(3, cocktail.getIcon());
            preparedStatement.setInt(4, cocktail.getUserId());
            return  !preparedStatement.execute();
        } catch (SQLException e) {
            throw  new DaoException("Can't handle insert request", e);
        }
    }

    @Override
    public List<Cocktail> getAll() throws DaoException {
        List<Cocktail> cocktails = new ArrayList<>();
        try(Connection connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_COCKTAILS_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Cocktail cocktail = new Cocktail();
                cocktail.setCocktailId(resultSet.getInt(ID));
                cocktail.setName(resultSet.getString(COCKTAILS_NAME));
                cocktail.setDescription(resultSet.getString(COCKTAILS_DESCRIPTION));
                cocktail.setUserId(resultSet.getInt(COCKTAILS_USER_ID));
                cocktail.setIcon(resultSet.getBlob(COCKTAILS_ICON).getBinaryStream());
                cocktails.add(cocktail);
            }
        } catch (SQLException e) {
            throw  new DaoException("Can't handle get all request", e);
        }
        return cocktails;
    }

    @Override
    public int getCocktailsAmount() throws DaoException {
        try(Connection connection = connectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(COUNT_COCKTAILS_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return resultSet.getInt(1);
            }
            else {
                throw new DaoException();
            }
        } catch (SQLException e) {
            throw new DaoException("Can't handle get amount request", e);
        }
    }

    @Override
    public List<Cocktail> getLimited(int start, int amount) throws DaoException {
        List<Cocktail> cocktails = new ArrayList<>();
        try(Connection connection = connectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_LIMIT_COCKTAILS_SQL)) {
            preparedStatement.setInt(1, start);
            preparedStatement.setInt(2, amount);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Cocktail cocktail = new Cocktail();
                cocktail.setCocktailId(resultSet.getInt(ID));
                cocktail.setName(resultSet.getString(COCKTAILS_NAME));
                cocktail.setDescription(resultSet.getString(COCKTAILS_DESCRIPTION));
                cocktail.setUserId(resultSet.getInt(COCKTAILS_USER_ID));
                cocktail.setIcon(resultSet.getBlob(COCKTAILS_ICON).getBinaryStream());
                cocktails.add(cocktail);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't handle get limited request", e);
        }
        return cocktails;
    }
}