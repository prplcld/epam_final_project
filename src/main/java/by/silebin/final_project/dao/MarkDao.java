package by.silebin.final_project.dao;

import by.silebin.final_project.entity.Mark;
import by.silebin.final_project.exception.DaoException;

import java.util.Optional;

/**
 * Interface provides methods to interact with Mark data from database.
 * Methods should connect to database and manipulate with data(save, edit, etc.).
 */
public interface MarkDao {

    /**
     * Connects to database and inserts mark.
     *
     * @param mark is {@link Mark} object that contains information for insert.
     * @throws DaoException when problems with database connection occurs.
     */
    boolean insert(Mark mark) throws DaoException;

    /**
     * Connects to database and updates mark.
     *
     * @param mark is {@link Mark} object that contains information for update.
     * @throws DaoException when problems with database connection occurs.
     */
    boolean update(Mark mark) throws DaoException;

    /**
     * Connects to database and gets average mark for user.
     *
     * @param targetUserId is user ID value.
     * @return average mark.
     * @throws DaoException when problems with database connection occurs.
     */
    int getAverageByTargetUserId(int targetUserId) throws DaoException;

    /**
     * Connects to database and returns mark made by particular user on another user.
     *
     * @param targetUserId is ID value of user that receives mark.
     * @param markUserId is ID value of user that places mark.
     * @return {@link Optional<Mark>}.
     * @throws DaoException when problems with database connection occurs.
     */
    Optional<Mark> getByUserIds(int targetUserId, int markUserId) throws DaoException;

    /**
     * Connects to database and deletes mark.
     *
     * @param markId is {@link Mark} ID value.
     * @throws DaoException when problems with database connection occurs.
     */
    boolean delete(int markId) throws DaoException;
}
