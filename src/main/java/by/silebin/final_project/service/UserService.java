package by.silebin.final_project.service;

import by.silebin.final_project.entity.User;
import by.silebin.final_project.entity.dto.UserStatDto;
import by.silebin.final_project.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> login(String login, String password) throws ServiceException;

    Optional<User> getById(int id) throws ServiceException;

    boolean register(String login, String password, String email) throws ServiceException;

    List<UserStatDto> getUsersStat() throws ServiceException;
}
