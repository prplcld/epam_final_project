package by.silebin.final_project.service;

import by.silebin.final_project.entity.Role;
import by.silebin.final_project.entity.User;
import by.silebin.final_project.entity.dto.UserStatDto;
import by.silebin.final_project.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService {

    /**
     *
     * @param login user login
     * @param password user password
     * @return {@link Optional<User>} optional of user
     * @throws ServiceException
     */
    Optional<User> login(String login, String password) throws ServiceException;

    /**
     *
     * @param user {@link User} object containing information about user
     * @param password user old password
     * @param login user old login
     * @return success of the operation
     * @throws ServiceException
     */
    boolean update(User user, String password, String login) throws ServiceException;

    /**
     *
     * @param id user ID value
     * @return {@link Optional<User>} optional of user
     * @throws ServiceException
     */
    Optional<User> getById(int id) throws ServiceException;

    /**
     *
     * @param login user login
     * @param password user password
     * @param email user email
     * @return success of the operation
     * @throws ServiceException
     */
    boolean register(String login, String password, String email) throws ServiceException;

    /**
     *
     * @return {@link List<UserStatDto>} list of users stat
     * @throws ServiceException
     */
    List<UserStatDto> getUsersStat() throws ServiceException;

    /**
     *
     * @param userId user ID value
     * @param role user role
     * @return success of the operation
     * @throws ServiceException
     */
    boolean updateUserRole(int userId, Role role) throws ServiceException;
}
