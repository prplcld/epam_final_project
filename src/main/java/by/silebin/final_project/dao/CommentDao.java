package by.silebin.final_project.dao;

import by.silebin.final_project.entity.Comment;
import by.silebin.final_project.entity.dto.CommentDto;
import by.silebin.final_project.exception.DaoException;

import java.util.List;

public interface CommentDao {
    List<CommentDto> getByCocktailId(int cocktailId) throws DaoException;

    boolean insert(Comment comment) throws DaoException;

    boolean delete(int commentId) throws DaoException;

    boolean update(Comment comment) throws DaoException;


}
