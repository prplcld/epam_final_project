package by.silebin.final_project.model.dao.impl;

import by.silebin.final_project.entity.User;
import by.silebin.final_project.exception.DaoException;
import by.silebin.final_project.model.dao.UserDao;
import by.silebin.final_project.model.pool.ConnectionPool;
import by.silebin.final_project.util.HashUtil;

import java.sql.*;
import java.util.Optional;
import static by.silebin.final_project.model.dao.ColumnName.*;

public class UserDaoImpl implements UserDao {

    private static final UserDaoImpl instance = new UserDaoImpl();
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String GET_USER_BY_LOGIN_SQL = "select id, login, password, email, role_id, icon from users where login = ?";
    private static final String INSERT_USER_SQL = "insert into users(login, password, email, role_id, icon) values(?, ?, ?, ?, ?)";
    private static final String SELECT_USER_BY_ID = "select login, password, email, role_id, icon from users where id = ?";
    private static final String UPDATE_USER = "update users set login = ?, password = ?, email = ?, role_id = ?, icon = ? where id = ?";

    private UserDaoImpl() {

    }

    public static UserDaoImpl getInstance() {
        return instance;
    }

    @Override
    public Optional<User> login(String login, String password) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_LOGIN_SQL)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                String hashSaltPassword = resultSet.getString(USERS_PASSWORD);
                if (!HashUtil.check(password, hashSaltPassword)) {
                    return Optional.empty();
                }

                User user = new User();
                user.setUserId(resultSet.getInt(ID));
                user.setLogin(resultSet.getString(USERS_LOGIN));
                return Optional.of(user);
            }

        } catch (SQLException e) {
           throw  new DaoException();
        }
        return Optional.empty();
    }

    @Override
    public boolean register(String login, String password) throws DaoException {
        try(Connection connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)) {
            String hashSaltPassword = HashUtil.hash(password);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, hashSaltPassword);
            preparedStatement.setString(3, "default@gmail.com");
            preparedStatement.setInt(4, 1);
            preparedStatement.setBlob(5, (Blob) null);
            return !preparedStatement.execute();
        } catch (SQLException e) {
            throw  new DaoException();
        }
    }

    @Override
    public Optional<User> findById(int id) throws DaoException {
        try(Connection connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                User user = new User();
                user.setUserId(id);
                user.setLogin(resultSet.getString(USERS_LOGIN));
                user.setPassword(resultSet.getString(USERS_PASSWORD));
                user.setEmail(resultSet.getString(USERS_EMAIL));
                return Optional.of(user);
            }
        } catch (SQLException e) {
            throw  new DaoException();
        }
        return Optional.empty();
    }

    @Override
    public boolean update(User user) throws DaoException {
        try(Connection connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setInt(4, 1);
            preparedStatement.setBlob(5, (Blob) null);
            return !preparedStatement.execute();
        } catch (SQLException e) {
            throw  new DaoException();
        }
    }
}
