package by.silebin.final_project.dao;

import by.silebin.final_project.entity.Role;
import by.silebin.final_project.entity.User;
import by.silebin.final_project.entity.dto.UserStatDto;
import by.silebin.final_project.exception.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * Interface provides methods to interact with User data from database.
 * Methods should connect to database and manipulate with data(save, edit, etc.).
 */
public interface UserDao {

    /**
     * Connects to database and logs in user.
     *
     * @param login    is {@link User} login.
     * @param password is {@link User} password.
     * @return {@link Optional<User>}.
     * @throws DaoException when problems with database connection occurs.
     */
    Optional<User> login(String login, String password) throws DaoException;

    /**
     * Connects to database and registers user.
     *
     * @param login    is {@link User} login.
     * @param password is {@link User} password.
     * @param email    is {@link User} email.
     * @throws DaoException when problems with database connection occurs.
     */
    boolean register(String login, String password, String email) throws DaoException;

    /**
     * Connects to database and returns user by id.
     *
     * @param id is {@link User} ID value.
     * @return {@link Optional<User>}.
     * @throws DaoException when problems with database connection occurs.
     */
    Optional<User> findById(int id) throws DaoException;

    /**
     * Connects to database and updates user.
     *
     * @param user is {@link User} object containing information.
     * @throws DaoException when problems with database connection occurs.
     */
    boolean update(User user) throws DaoException;

    /**
     * Connects to database and returns stats of users.
     *
     * @return {@link List<UserStatDto>}.
     * @throws DaoException when problems with database connection occurs.
     */
    List<UserStatDto> getUsersStat() throws DaoException;

    /**
     * Connects to database and updates user role.
     *
     * @param role is {@link Role} enum value.
     * @param userId is user ID value
     * @throws DaoException when problems with database connection occurs.
     */
    boolean updateUserRole(int userId, Role role) throws DaoException;
}
