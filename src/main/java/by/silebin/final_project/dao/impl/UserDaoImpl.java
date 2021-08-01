package by.silebin.final_project.dao.impl;

import by.silebin.final_project.entity.User;
import by.silebin.final_project.exception.DaoException;
import by.silebin.final_project.dao.UserDao;
import by.silebin.final_project.pool.ConnectionPool;
import by.silebin.final_project.util.HashUtil;

import java.sql.*;
import java.util.Optional;
import static by.silebin.final_project.dao.ColumnName.*;

public class UserDaoImpl implements UserDao {

    private static final UserDaoImpl instance = new UserDaoImpl();
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String GET_USER_BY_LOGIN_SQL = "select id, login, password, email, role_id from users where login = ?";
    private static final String INSERT_USER_SQL = "insert into users(login, password, email, role_id) values(?, ?, ?, ?, ?)";
    private static final String SELECT_USER_BY_ID = "select login, password, email, role_id from users where id = ?";
    private static final String UPDATE_USER = "update users set login = ?, password = ?, email = ?, role_id = ? where id = ?";

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
                //FIXME join role
                return Optional.of(user);
            }

        } catch (SQLException e) {
           throw  new DaoException("Can't handle login request", e);
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
            return !preparedStatement.execute();
        } catch (SQLException e) {
            throw  new DaoException("Can't handle register request", e);
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
                user.setEmail(resultSet.getString(USERS_EMAIL));
                return Optional.of(user);
            }
        } catch (SQLException e) {
            throw  new DaoException("Can't handle find by id request", e);
        }
        return Optional.empty();
    }

    @Override
    public boolean update(User user) throws DaoException {
        try(Connection connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, "213"); //FIXME change method to take password and confirm password
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setInt(4, 1); // FIXME manage role foreign key
            return !preparedStatement.execute();
        } catch (SQLException e) {
            throw  new DaoException("Can't handle update request", e);
        }
    }
}
