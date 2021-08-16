package by.silebin.final_project.dao;

import by.silebin.final_project.entity.Comment;
import by.silebin.final_project.entity.dto.CommentDto;
import by.silebin.final_project.exception.DaoException;

import java.util.List;

/**
 * Interface provides methods to interact with Comment data from database.
 * Methods should connect to database and manipulate with data(save, edit, etc.).
 */
public interface CommentDao {
    /**
     * Connects to database and returns list of comments for cocktail.
     *
     * @param cocktailId is cocktail ID value;
     * @return list of comments for cocktail
     * @throws DaoException when problems with database connection occurs.
     */
    List<CommentDto> getByCocktailId(int cocktailId) throws DaoException;

    /**
     * Connects to database and inserts comment.
     *
     * @param comment is {@link Comment} object that contains information for insert.
     * @throws DaoException when problems with database connection occurs.
     */
    boolean insert(Comment comment) throws DaoException;

    /**
     * Connects to database and deletes comment.
     *
     * @param commentId is {@link Comment} ID value.
     * @throws DaoException when problems with database connection occurs.
     */
    boolean delete(int commentId) throws DaoException;

    /**
     * Connects to database and updated comment.
     *
     * @param comment is {@link Comment} object that contains information for update.
     * @throws DaoException when problems with database connection occurs.
     */
    boolean update(Comment comment) throws DaoException;


}
