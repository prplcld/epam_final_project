package by.silebin.final_project.entity.dto;

public class CommentDto {
    private String text;
    private String login;
    private int userId;
    private int commentId;
    private int mark;


    public CommentDto() {
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentDto that = (CommentDto) o;

        if (userId != that.userId) return false;
        if (commentId != that.commentId) return false;
        if (mark != that.mark) return false;
        if (!text.equals(that.text)) return false;
        return login.equals(that.login);
    }

    @Override
    public int hashCode() {
        int result = text.hashCode();
        result = 31 * result + login.hashCode();
        result = 31 * result + userId;
        result = 31 * result + commentId;
        result = 31 * result + mark;
        return result;
    }

    @Override
    public String toString() {
        return "CommentDto{" +
                "text='" + text + '\'' +
                ", login='" + login + '\'' +
                ", userId=" + userId +
                ", commentId=" + commentId +
                ", mark=" + mark +
                '}';
    }
}
