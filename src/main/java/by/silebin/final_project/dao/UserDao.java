package by.silebin.final_project.dao;

import by.silebin.final_project.entity.User;
import by.silebin.final_project.exception.DaoException;

import java.util.Optional;

public interface UserDao {
    Optional<User> login(String login, String password) throws DaoException;

    boolean register(String login, String password) throws DaoException;

    Optional<User> findById(int id) throws DaoException;

    boolean update(User user) throws DaoException;
}
