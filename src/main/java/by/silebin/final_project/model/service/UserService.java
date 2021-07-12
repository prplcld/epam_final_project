package by.silebin.final_project.model.service;

import by.silebin.final_project.entity.User;
import by.silebin.final_project.exception.ServiceException;

import java.util.Optional;

public interface UserService {
    Optional<User> login(String login, String password) throws ServiceException;
}
