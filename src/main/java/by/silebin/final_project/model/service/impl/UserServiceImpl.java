package by.silebin.final_project.model.service.impl;

import by.silebin.final_project.entity.User;
import by.silebin.final_project.model.dao.UserDao;
import by.silebin.final_project.model.dao.impl.UserDaoImpl;
import by.silebin.final_project.model.service.UserService;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    @Override
    public Optional<User> login(String login, String password) {
        UserDao userDao = UserDaoImpl.getInstance();
        return  userDao.login(login, password);
    }
}
