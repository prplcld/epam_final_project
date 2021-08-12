package by.silebin.final_project.dao;

import by.silebin.final_project.entity.Role;
import by.silebin.final_project.entity.User;
import by.silebin.final_project.entity.dto.UserStatDto;
import by.silebin.final_project.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    Optional<User> login(String login, String password) throws DaoException;

    boolean register(String login, String password, String email) throws DaoException;

    Optional<User> findById(int id) throws DaoException;

    boolean update(User user) throws DaoException;

    List<UserStatDto> getUsersStat() throws DaoException;

    boolean updateUserRole(int userId, Role role) throws DaoException;
}
