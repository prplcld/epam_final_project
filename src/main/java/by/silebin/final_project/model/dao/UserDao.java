package by.silebin.final_project.model.dao;

import by.silebin.final_project.entity.User;

import java.util.Optional;

public interface UserDao {
    Optional<User> login(String login, String password);
    boolean register(String login, String password);
    Optional<User> findById(int id);
    boolean update(User user);
}
