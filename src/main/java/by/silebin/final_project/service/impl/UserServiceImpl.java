package by.silebin.final_project.service.impl;

import by.silebin.final_project.entity.User;
import by.silebin.final_project.exception.DaoException;
import by.silebin.final_project.exception.ServiceException;
import by.silebin.final_project.dao.UserDao;
import by.silebin.final_project.dao.impl.UserDaoImpl;
import by.silebin.final_project.service.UserService;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    @Override
    public Optional<User> login(String login, String password) throws ServiceException {
        UserDao userDao = UserDaoImpl.getInstance();
        try {
            Optional<User> user = userDao.login(login, password);
            return user;
        } catch (DaoException e) {
            throw  new ServiceException("Can't handle login at UserService", e);
        }
    }
}
