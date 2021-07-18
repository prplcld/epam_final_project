package by.silebin.final_project.entity;

import java.util.Objects;

public class Comment {
    private int commentId;
    private String text;
    private int cocktailId;
    private int mark;
    private int userId;

    public Comment() {
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getCocktailId() {
        return cocktailId;
    }

    public void setCocktailId(int cocktailId) {
        this.cocktailId = cocktailId;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return commentId == comment.commentId && cocktailId == comment.cocktailId && mark == comment.mark && userId == comment.userId && text.equals(comment.text);
    }

    @Override
    public int hashCode() {
        //FIXME
        return Objects.hash(commentId, text, cocktailId, mark, userId);
    }
}
