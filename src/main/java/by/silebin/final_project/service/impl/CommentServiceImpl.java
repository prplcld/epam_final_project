package by.silebin.final_project.service.impl;

import by.silebin.final_project.dao.CommentDao;
import by.silebin.final_project.dao.impl.CommentDaoImpl;
import by.silebin.final_project.entity.Comment;
import by.silebin.final_project.entity.dto.CommentDto;
import by.silebin.final_project.exception.DaoException;
import by.silebin.final_project.exception.ServiceException;
import by.silebin.final_project.service.CommentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CommentServiceImpl implements CommentService {

    private static final Logger logger = LogManager.getLogger(CommentServiceImpl.class);

    private static final CommentServiceImpl instance = new CommentServiceImpl();

    public static CommentServiceImpl getInstance() {
        return instance;
    }

    private CommentServiceImpl() {

    }

    @Override
    public List<CommentDto> getCommentsForCocktail(int cocktailId) throws ServiceException {
        CommentDao commentDao = CommentDaoImpl.getInstance();
        try {
            return commentDao.getByCocktailId(cocktailId);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException("Can't handle CommentServiceImpl.getCommentsForCocktail", e);
        }
    }

    @Override
    public boolean leaveComment(Comment comment) throws ServiceException {
        CommentDao commentDao = CommentDaoImpl.getInstance();

        try {
            return commentDao.insert(comment);
        } catch (DaoException e) {
            logger.error(e);
            throw  new ServiceException("Can't handle CommentServiceImpl.leaveComment", e);
        }
    }

    @Override
    public boolean delete(int commentId) throws ServiceException {
        CommentDao commentDao = CommentDaoImpl.getInstance();

        try {
            return commentDao.delete(commentId);
        } catch (DaoException e) {
            logger.error(e);
            throw  new ServiceException("Can't handle CommentServiceImpl.delete", e);
        }
    }
}
