package by.silebin.final_project.dao.impl;

import by.silebin.final_project.dao.UserDao;
import by.silebin.final_project.entity.Role;
import by.silebin.final_project.entity.User;
import by.silebin.final_project.entity.dto.UserStatDto;
import by.silebin.final_project.exception.DaoException;
import by.silebin.final_project.pool.ConnectionPool;
import by.silebin.final_project.util.HashUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.silebin.final_project.dao.ColumnName.*;

public class UserDaoImpl implements UserDao {

    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);

    private static final UserDaoImpl instance = new UserDaoImpl();
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String GET_USER_BY_LOGIN_SQL = "select u.id, u.login, u.password, email, r.name from users u join roles r on u.role_id = r.id where login = ?";
    private static final String INSERT_USER_SQL = "insert into users(login, password, email, role_id) values(?, ?, ?, ?)";
    private static final String SELECT_USER_BY_ID = "select u.login, u.email, r.name from users u join roles r on role_id = r.id where u.id = ?";
    private static final String UPDATE_USER = "update users set login = ?, password = ?, email = ?, role_id = ? where id = ?";
    private static final String GET_USER_STAT = "select u.id, u.login, avg(m.mark), count(c.id), r.name from users u " +
            "join marks m on m.target_user_id = u.id " +
            "join cocktails c on c.user_id = u.id " +
            "join roles r on u.role_id = r.id " +
            "where u.role_id != 1 " +
            "group by u.login";
    private static final int defaultRoleId = 2;

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
                user.setRole(Role.valueOf(resultSet.getString(USERS_ROLE_NAME).toUpperCase()));
                return Optional.of(user);
            }

        } catch (SQLException e) {
            logger.error(e);
           throw  new DaoException("Can't handle UserDaoImpl.login request", e);
        }
        return Optional.empty();
    }

    @Override
    public boolean register(String login, String password, String email) throws DaoException {
        try(Connection connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)) {
            String hashSaltPassword = HashUtil.hash(password);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, hashSaltPassword);
            preparedStatement.setString(3, email);
            preparedStatement.setInt(4, defaultRoleId);
            return !preparedStatement.execute();
        } catch (SQLException e) {
            logger.error(e);
            throw  new DaoException("Can't handle UserDaoImpl.register request", e);
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
                user.setRole(Role.valueOf(resultSet.getString(USERS_ROLE_NAME).toUpperCase()));
                return Optional.of(user);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw  new DaoException("Can't handle UserDaoImpl.findById request", e);
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
            logger.error(e);
            throw  new DaoException("Can't handle UserDaoImpl.update request", e);
        }
    }

    @Override
    public List<UserStatDto> getUsersStat() throws DaoException {
        List<UserStatDto> userStatDtoList = new ArrayList<>();
        try(Connection connection = connectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_STAT)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UserStatDto userStatDto = new UserStatDto();
                userStatDto.setUserId(resultSet.getInt(ID));
                userStatDto.setLogin(resultSet.getString(USERS_LOGIN));
                userStatDto.setAverageMark((int)resultSet.getDouble(MARKS_M_AVG));
                userStatDto.setCocktailsAmount(resultSet.getInt(COCKTAILS_COUNT));
                userStatDto.setRole(Role.valueOf(resultSet.getString(USERS_ROLE_NAME).toUpperCase()));
                userStatDtoList.add(userStatDto);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw  new DaoException("Can't handle UserDaoImpl.getUsersStat request", e);
        }
        return userStatDtoList;
    }
}
