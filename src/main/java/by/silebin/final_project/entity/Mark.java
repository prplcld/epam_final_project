package by.silebin.final_project.entity;

public class Mark {
    private int markId;
    private int mark;
    private int targetUserId;
    private int markUserId;

    public Mark() {
    }

    public int getMarkId() {
        return markId;
    }

    public void setMarkId(int markId) {
        this.markId = markId;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(int targetUserId) {
        this.targetUserId = targetUserId;
    }

    public int getMarkUserId() {
        return markUserId;
    }

    public void setMarkUserId(int markUserId) {
        this.markUserId = markUserId;
    }
}
