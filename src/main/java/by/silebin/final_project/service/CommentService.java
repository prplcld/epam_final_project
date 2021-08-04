package by.silebin.final_project.service;

import by.silebin.final_project.entity.Comment;
import by.silebin.final_project.entity.dto.CommentDto;
import by.silebin.final_project.exception.ServiceException;

import java.util.List;

public interface CommentService {
    List<CommentDto> getCommentsForCocktail(int cocktailId) throws ServiceException;

    boolean leaveComment(Comment comment) throws ServiceException;
}
