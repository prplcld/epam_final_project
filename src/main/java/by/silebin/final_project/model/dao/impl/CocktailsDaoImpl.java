package by.silebin.final_project.model.dao.impl;

import by.silebin.final_project.entity.Cocktail;
import by.silebin.final_project.exception.DaoException;
import by.silebin.final_project.model.dao.CocktailsDao;
import by.silebin.final_project.model.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static by.silebin.final_project.model.dao.ColumnName.*;


public class CocktailsDaoImpl implements CocktailsDao {

    private static final CocktailsDaoImpl instance = new CocktailsDaoImpl();
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String GET_COCKTAIL_BY_ID_SQL = "select id, name, description, user_id from cocktails where id = ?";
    private static final String GET_ALL_COCKTAILS_SQL = "select id, name, description, icon, user_id from cocktails";
    private static final String INSERT_COCKTAIL_SQL = "insert into cocktails(name, description, icon, user_id) values(?, ?, ?, ?)";

    private CocktailsDaoImpl() {}

    public static CocktailsDaoImpl getInstance() {
        return instance;
    }

    @Override
    public Optional<Cocktail> findById(int id) {
        return Optional.empty();
    }

    @Override
    public boolean update(Cocktail cocktail) {
        return false;
    }

    @Override
    public boolean delete(Cocktail cocktail) {
        return false;
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
            throw  new DaoException();
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
            throw  new DaoException();
        }
        return cocktails;
    }
}
