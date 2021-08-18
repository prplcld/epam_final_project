package by.silebin.final_project.service.impl;

import by.silebin.final_project.dao.UserDao;
import by.silebin.final_project.dao.impl.UserDaoImpl;
import by.silebin.final_project.entity.Role;
import by.silebin.final_project.entity.User;
import by.silebin.final_project.entity.dto.UserStatDto;
import by.silebin.final_project.exception.DaoException;
import by.silebin.final_project.exception.ServiceException;
import by.silebin.final_project.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private static final UserServiceImpl instance = new UserServiceImpl();

    public static UserServiceImpl getInstance() {
        return instance;
    }

    private UserServiceImpl() {

    }

    @Override
    public Optional<User> login(String login, String password) throws ServiceException {
        UserDao userDao = UserDaoImpl.getInstance();
        try {
            Optional<User> user = userDao.login(login, password);
            return user;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException("Can't handle UserServiceImpl.login", e);
        }
    }

    @Override
    public Optional<User> getById(int id) throws ServiceException {
        UserDao userDao = UserDaoImpl.getInstance();
        try {
            Optional<User> user = userDao.findById(id);
            return user;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException("Can't handle UserServiceImpl.getById", e);
        }
    }

    @Override
    public boolean register(String login, String password, String email) throws ServiceException {
        UserDao userDao = UserDaoImpl.getInstance();
        try {
            return userDao.register(login, password, email);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException("Can't handle UserServiceImpl.register", e);
        }
    }

    @Override
    public List<UserStatDto> getUsersStat() throws ServiceException {
        UserDao userDao = UserDaoImpl.getInstance();
        try {
            return userDao.getUsersStat();
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException("Can't handle UserServiceImpl.getUsersStat", e);
        }
    }

    @Override
    public boolean updateUserRole(int userId, Role role) throws ServiceException {
        UserDao userDao = UserDaoImpl.getInstance();
        try {
            return userDao.updateUserRole(userId, role);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException("Can't handle UserServiceImpl.updateUserRole", e);
        }
    }

    @Override
    public boolean update(User user, String password, String login) throws ServiceException {
        UserDao userDao = UserDaoImpl.getInstance();

        try {
            Optional<User> userOptional = userDao.login(login, password);
            if (userOptional.isPresent()) {
                userDao.update(user);
                return true;
            } else return false;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException("Can't handle UserServiceImpl.update", e);
        }
    }
}
