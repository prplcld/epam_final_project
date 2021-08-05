package by.silebin.final_project.service.impl;

import by.silebin.final_project.dao.UserDao;
import by.silebin.final_project.dao.impl.UserDaoImpl;
import by.silebin.final_project.entity.User;
import by.silebin.final_project.exception.DaoException;
import by.silebin.final_project.exception.ServiceException;
import by.silebin.final_project.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Override
    public Optional<User> login(String login, String password) throws ServiceException {
        UserDao userDao = UserDaoImpl.getInstance();
        try {
            Optional<User> user = userDao.login(login, password);
            return user;
        } catch (DaoException e) {
            logger.error(e);
            throw  new ServiceException(e);
        }
    }

    @Override
    public Optional<User> getById(int id) throws ServiceException {
        UserDao userDao = UserDaoImpl.getInstance();
        try {
            Optional<User> user =userDao.findById(id);
            return user;
        } catch (DaoException e) {
            logger.error(e);
            throw  new ServiceException(e);
        }
    }

    @Override
    public boolean register(String login, String password, String email) throws ServiceException {
        UserDao userDao = UserDaoImpl.getInstance();
        try {
            return userDao.register(login, password, email);
        } catch (DaoException e) {
            logger.error(e);
           throw new ServiceException(e);
        }
    }
}
