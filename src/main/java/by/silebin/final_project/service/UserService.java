package by.silebin.final_project.service;

import by.silebin.final_project.entity.Role;
import by.silebin.final_project.entity.User;
import by.silebin.final_project.entity.dto.UserStatDto;
import by.silebin.final_project.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> login(String login, String password) throws ServiceException;

    boolean update(User user, String password, String login) throws ServiceException;

    Optional<User> getById(int id) throws ServiceException;

    boolean register(String login, String password, String email) throws ServiceException;

    List<UserStatDto> getUsersStat() throws ServiceException;

    boolean updateUserRole(int userId, Role role) throws ServiceException;
}
