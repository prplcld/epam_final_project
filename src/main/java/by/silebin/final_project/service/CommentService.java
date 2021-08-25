package by.silebin.final_project.service;

import by.silebin.final_project.entity.Comment;
import by.silebin.final_project.entity.dto.CommentDto;
import by.silebin.final_project.exception.ServiceException;

import java.util.List;

public interface CommentService {

    /**
     *
     * @param cocktailId ID value of cocktail
     * @return {@link List<CommentDto>} list of comments with corresponding users
     * @throws ServiceException
     */
    List<CommentDto> getCommentsForCocktail(int cocktailId) throws ServiceException;

    /**
     *
     * @param comment {@link Comment} object containing info
     * @return success of the operation
     * @throws ServiceException
     */
    boolean leaveComment(Comment comment) throws ServiceException;

    /**
     *
     * @param commentId comment ID value
     * @return success of the operation
     * @throws ServiceException
     */
    boolean delete(int commentId) throws ServiceException;
}
