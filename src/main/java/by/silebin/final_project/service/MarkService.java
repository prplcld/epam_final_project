package by.silebin.final_project.service;

import by.silebin.final_project.entity.Mark;
import by.silebin.final_project.exception.ServiceException;

import java.util.Optional;

public interface MarkService {
    int getAverageUserMark(int userId) throws ServiceException;

    Optional<Mark> getMark(int targetUserId, int markUserId) throws ServiceException;

    boolean saveMark(Mark mark) throws ServiceException;
}
