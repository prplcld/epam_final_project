package by.silebin.final_project.dao.impl;

import by.silebin.final_project.dao.CommentDao;
import by.silebin.final_project.entity.Comment;
import by.silebin.final_project.exception.DaoException;
import by.silebin.final_project.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.silebin.final_project.dao.ColumnName.*;

public class CommentDaoImpl implements CommentDao {

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final CommentDaoImpl instance = new CommentDaoImpl();

    private static final String GET_COMMENTS_BY_COCKTAIL_ID_SQL = "select id, text, cocktail_id, mark, user_id from comments where cocktail_id = ?";
    private static final String INSERT_COMMENT_SQL = "insert into comments(text, mark, cocktail_id, user_id) values(?, ?, ?, ?)";
    private static final String UPDATE_COMMENT_SQL = "update comments set text = ?, mark = ?, cocktail_id = ?, user_id = ? where id = ?";
    private static final String DELETE_COMMENT_SQL = "delete from comments where id = ?";

    private CommentDaoImpl() {
    }

    public CommentDaoImpl getInstance() {
        return instance;
    }

    @Override
    public List<Comment> getByCocktailId(int cocktailId) throws DaoException {
        List<Comment> comments = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_COMMENTS_BY_COCKTAIL_ID_SQL)) {
            preparedStatement.setInt(1, cocktailId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Comment comment = new Comment();
                comment.setCommentId(resultSet.getInt(ID));
                comment.setText(resultSet.getString(COMMENTS_COMMENT));
                comment.setMark(resultSet.getInt(COMMENTS_MARK));
                comment.setCocktailId(resultSet.getInt(COMMENTS_COCKTAIL_ID));
                comment.setUserId(resultSet.getInt(COMMENTS_USER_ID));
                comments.add(comment);
            }
        } catch (SQLException e) {
            throw new DaoException();
        }
        return comments;
    }

    @Override
    public boolean insert(Comment comment) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COMMENT_SQL)) {
            preparedStatement.setString(1, comment.getText());
            preparedStatement.setInt(2, comment.getMark());
            preparedStatement.setInt(3, comment.getCocktailId());
            preparedStatement.setInt(4, comment.getUserId());
            return !preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    @Override
    public boolean delete(int commentId) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_COMMENT_SQL)) {
            preparedStatement.setInt(1, commentId);
            return !preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    @Override
    public boolean update(Comment comment) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COMMENT_SQL)) {
            preparedStatement.setString(1, comment.getText());
            preparedStatement.setInt(2, comment.getMark());
            preparedStatement.setInt(3, comment.getCocktailId());
            preparedStatement.setInt(4, comment.getUserId());
            preparedStatement.setInt(5, comment.getCommentId());
            return !preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException();
        }
    }
}
