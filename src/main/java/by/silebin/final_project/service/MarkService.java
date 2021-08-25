package by.silebin.final_project.service;

import by.silebin.final_project.entity.Mark;
import by.silebin.final_project.exception.ServiceException;
import java.util.Optional;


public interface MarkService {

    /**
     *
     * @param userId user ID value
     * @return average user mark
     * @throws ServiceException
     */
    int getAverageUserMark(int userId) throws ServiceException;

    /**
     *
     * @param targetUserId ID value of user that receive mark
     * @param markUserId ID value of user that marks other user
     * @return {@link Optional<Mark>}
     * @throws ServiceException
     */
    Optional<Mark> getMark(int targetUserId, int markUserId) throws ServiceException;

    /**
     *
     * @param mark {@link Mark} object containing information
     * @return success of the operation
     * @throws ServiceException
     */
    boolean saveMark(Mark mark) throws ServiceException;
}
