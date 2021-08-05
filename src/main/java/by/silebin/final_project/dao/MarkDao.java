package by.silebin.final_project.dao;

import by.silebin.final_project.entity.Mark;
import by.silebin.final_project.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface MarkDao {

    boolean insert(Mark mark) throws DaoException;

    boolean update(Mark mark) throws DaoException;

    int getAverageByTargetUserId(int targetUserId) throws DaoException;

    Optional<Mark> getByUserIds(int targetUserId, int markUserId) throws DaoException;

    boolean delete(int markId) throws DaoException;
}
