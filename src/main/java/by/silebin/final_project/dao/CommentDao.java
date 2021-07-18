package by.silebin.final_project.dao;

import by.silebin.final_project.entity.Comment;
import by.silebin.final_project.exception.DaoException;

public interface CommentDao {
    Comment getByCocktailId(int cocktailId) throws DaoException;

    boolean insert(Comment comment) throws DaoException;

    boolean delete(int commentId) throws DaoException;

    boolean update(Comment comment) throws DaoException;

}
