package by.silebin.final_project.service.impl;

import by.silebin.final_project.dao.MarkDao;
import by.silebin.final_project.dao.impl.MarkDaoImpl;
import by.silebin.final_project.entity.Mark;
import by.silebin.final_project.exception.DaoException;
import by.silebin.final_project.exception.ServiceException;
import by.silebin.final_project.service.MarkService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class MarkServiceImpl implements MarkService {

    private static final Logger logger = LogManager.getLogger(MarkServiceImpl.class);

    private static final MarkServiceImpl instance = new MarkServiceImpl();

    public static MarkServiceImpl getInstance() {
        return instance;
    }

    private MarkServiceImpl() {

    }

    @Override
    public int getAverageUserMark(int userId) throws ServiceException {
        MarkDao markDao = MarkDaoImpl.getInstance();
        try {
            return markDao.getAverageByTargetUserId(userId);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException();
        }
    }

    @Override
    public Optional<Mark> getMark(int targetUserId, int markUserId) throws ServiceException {
        MarkDao markDao = MarkDaoImpl.getInstance();
        try {
            return markDao.getByUserIds(targetUserId, markUserId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean saveMark(Mark mark) throws ServiceException {
        MarkDao markDao = MarkDaoImpl.getInstance();
        try {
            if(markDao.getByUserIds(mark.getTargetUserId(), mark.getMarkUserId()).isPresent()) {
                markDao.update(mark);
            }
            else {
                markDao.insert(mark);
            }
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return false;
    }
}
