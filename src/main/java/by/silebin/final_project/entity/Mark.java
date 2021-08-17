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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mark mark1 = (Mark) o;

        if (markId != mark1.markId) return false;
        if (mark != mark1.mark) return false;
        if (targetUserId != mark1.targetUserId) return false;
        return markUserId == mark1.markUserId;
    }

    @Override
    public int hashCode() {
        int result = markId;
        result = 31 * result + mark;
        result = 31 * result + targetUserId;
        result = 31 * result + markUserId;
        return result;
    }

    @Override
    public String toString() {
        return "Mark{" +
                "markId=" + markId +
                ", mark=" + mark +
                ", targetUserId=" + targetUserId +
                ", markUserId=" + markUserId +
                '}';
    }
}
