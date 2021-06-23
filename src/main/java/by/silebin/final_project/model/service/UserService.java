package by.silebin.final_project.model.service;

import by.silebin.final_project.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> login(String login, String password);
}
