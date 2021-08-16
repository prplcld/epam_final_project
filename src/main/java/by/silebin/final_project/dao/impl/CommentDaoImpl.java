package by.silebin.final_project.dao.impl;

import by.silebin.final_project.dao.CommentDao;
import by.silebin.final_project.entity.Comment;
import by.silebin.final_project.entity.dto.CommentDto;
import by.silebin.final_project.exception.DaoException;
import by.silebin.final_project.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.silebin.final_project.dao.ColumnName.*;

/**
 * Implementation of {@link CommentDao}. Provides methods to interact with Comment data from database.
 * Methods connect to database using {@link Connection} from {@link ConnectionPool} and manipulate with data(save, edit, etc.).
 */
public class CommentDaoImpl implements CommentDao {

    private static final Logger logger = LogManager.getLogger(CommentDaoImpl.class);

    /** An object of {@link ConnectionPool} */
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    /**
     * A single instance of the class (pattern Singleton)
     */
    private static final CommentDaoImpl instance = new CommentDaoImpl();

    private static final String GET_COMMENTS_BY_COCKTAIL_ID_SQL = "select c.id, c.text, c.mark, c.user_id, u.login from comments c " +
            "join users u on c.user_id = u.id where cocktail_id = ?";
    private static final String INSERT_COMMENT_SQL = "insert into comments(text, mark, cocktail_id, user_id) values(?, ?, ?, ?)";
    private static final String UPDATE_COMMENT_SQL = "update comments set text = ?, mark = ?, cocktail_id = ?, user_id = ? where id = ?";
    private static final String DELETE_COMMENT_SQL = "delete from comments where id = ?";

    private CommentDaoImpl() {
    }

    /**
     * Returns the instance of the class
     * @return Object of {@link CommentDaoImpl}
     */
    public static CommentDaoImpl getInstance() {
        return instance;
    }

    /**
     * Connects to database and returns list of comments for cocktail.
     *
     * @param cocktailId is cocktail ID value;
     * @return list of comments for cocktail
     * @throws DaoException when problems with database connection occurs.
     */
    @Override
    public List<CommentDto> getByCocktailId(int cocktailId) throws DaoException {
        List<CommentDto> commentDtoList = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_COMMENTS_BY_COCKTAIL_ID_SQL)) {
            preparedStatement.setInt(1, cocktailId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                CommentDto commentDto = new CommentDto();
                commentDto.setCommentId(resultSet.getInt(ID));
                commentDto.setText(resultSet.getString(COMMENTS_COMMENT));
                commentDto.setMark(resultSet.getInt(COMMENTS_MARK));
                commentDto.setUserId(resultSet.getInt(COMMENTS_USER_ID));
                commentDto.setLogin(resultSet.getString(USERS_LOGIN));
                commentDtoList.add(commentDto);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException("Can't handle CommentDaoImpl.getByCocktailId", e);
        }
        return commentDtoList;
    }

    /**
     * Connects to database and inserts comment.
     *
     * @param comment is {@link Comment} object that contains information for insert.
     * @throws DaoException when problems with database connection occurs.
     */
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
            logger.error(e);
            throw new DaoException("Can't handle CommentDaoImpl.insert", e);
        }
    }

    /**
     * Connects to database and deletes comment.
     *
     * @param commentId is {@link Comment} ID value.
     * @throws DaoException when problems with database connection occurs.
     */
    @Override
    public boolean delete(int commentId) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_COMMENT_SQL)) {
            preparedStatement.setInt(1, commentId);
            return !preparedStatement.execute();
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException("Can't handle CommentDaoImpl.delete", e);
        }
    }

    /**
     * Connects to database and updated comment.
     *
     * @param comment is {@link Comment} object that contains information for update.
     * @throws DaoException when problems with database connection occurs.
     */
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
            logger.error(e);
            throw new DaoException("Can't handle CommentDaoImpl.update", e);
        }
    }
}
