package by.silebin.final_project.dao.impl;

import by.silebin.final_project.dao.MarkDao;
import by.silebin.final_project.entity.Mark;
import by.silebin.final_project.exception.DaoException;
import by.silebin.final_project.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static by.silebin.final_project.dao.ColumnName.MARKS_AVG;
import static by.silebin.final_project.dao.ColumnName.MARKS_MARK;

/**
 * Implementation of {@link MarkDao}. Provides methods to interact with Mark data from database.
 * Methods connect to database using {@link Connection} from {@link ConnectionPool} and manipulate with data(save, edit, etc.).
 */
public class MarkDaoImpl implements MarkDao {
    private static final Logger logger = LogManager.getLogger(MarkDaoImpl.class);

    /**
     * A single instance of the class (pattern Singleton)
     */
    private static final MarkDaoImpl instance = new MarkDaoImpl();

    /**
     * An object of {@link ConnectionPool}
     */
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String INSERT_MARK_SQL = "insert into marks(mark, target_user_id, mark_user_id) values(?, ?, ?)";
    private static final String UPDATE_MARK_SQL = "update marks set mark = ? where target_user_id = ? and  mark_user_id = ?";
    private static final String SELECT_AVERAGE_SQL = "select avg(mark) from marks where target_user_id = ?";
    private static final String GET_BY_USER_IDS_SQL = "select mark from marks where target_user_id = ? and  mark_user_id = ?";
    private static final String DELETE_MARK_SQL = "delete from marks where id = ?";


    /**
     * Private constructor without parameters
     */
    private MarkDaoImpl() {

    }

    /**
     * Returns the instance of the class
     *
     * @return Object of {@link MarkDaoImpl}
     */
    public static MarkDaoImpl getInstance() {
        return instance;
    }

    /**
     * Connects to database and inserts mark.
     *
     * @param mark is {@link Mark} object that contains information for insert.
     * @throws DaoException when problems with database connection occurs.
     */
    @Override
    public boolean insert(Mark mark) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MARK_SQL)) {
            preparedStatement.setInt(1, mark.getMark());
            preparedStatement.setInt(2, mark.getTargetUserId());
            preparedStatement.setInt(3, mark.getMarkUserId());
            return !preparedStatement.execute();
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException("Can't handle MarkDaoImpl.insert request", e);
        }
    }

    /**
     * Connects to database and updates mark.
     *
     * @param mark is {@link Mark} object that contains information for update.
     * @throws DaoException when problems with database connection occurs.
     */
    @Override
    public boolean update(Mark mark) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MARK_SQL)) {
            preparedStatement.setInt(1, mark.getMark());
            preparedStatement.setInt(2, mark.getTargetUserId());
            preparedStatement.setInt(3, mark.getMarkUserId());
            return !preparedStatement.execute();
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException("Can't handle MarkDaoImpl.update request", e);
        }
    }

    /**
     * Connects to database and gets average mark for user.
     *
     * @param targetUserId is user ID value.
     * @return average mark.
     * @throws DaoException when problems with database connection occurs.
     */
    @Override
    public int getAverageByTargetUserId(int targetUserId) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_AVERAGE_SQL)) {
            preparedStatement.setInt(1, targetUserId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return (int) resultSet.getDouble(MARKS_AVG);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException("Can't handle MarkDaoImpl.getAverageByTargetUserId request", e);
        }
        return 0;
    }

    /**
     * Connects to database and returns mark made by particular user on another user.
     *
     * @param targetUserId is ID value of user that receives mark.
     * @param markUserId   is ID value of user that places mark.
     * @return {@link Optional<Mark>}.
     * @throws DaoException when problems with database connection occurs.
     */
    @Override
    public Optional<Mark> getByUserIds(int targetUserId, int markUserId) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_USER_IDS_SQL)) {
            preparedStatement.setInt(1, targetUserId);
            preparedStatement.setInt(2, markUserId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Mark mark = new Mark();
                mark.setMark(resultSet.getInt(MARKS_MARK));
                mark.setMarkUserId(markUserId);
                mark.setTargetUserId(targetUserId);
                return Optional.of(mark);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException("Can't handle MarkDaoImpl.getByUserIds request", e);
        }
        return Optional.empty();
    }

    /**
     * Connects to database and deletes mark.
     *
     * @param markId is {@link Mark} ID value.
     * @throws DaoException when problems with database connection occurs.
     */
    @Override
    public boolean delete(int markId) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_MARK_SQL)) {
            preparedStatement.setInt(1, markId);
            return !preparedStatement.execute();
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException("Can't handle MarkDaoImpl.delete request", e);
        }
    }
}
