package by.silebin.final_project.entity.dto;

import by.silebin.final_project.entity.Role;

public class UserStatDto {
    private int userId;
    private String login;
    private int averageMark;
    private int cocktailsAmount;
    private Role role;

    public UserStatDto() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getAverageMark() {
        return averageMark;
    }

    public void setAverageMark(int averageMark) {
        this.averageMark = averageMark;
    }

    public int getCocktailsAmount() {
        return cocktailsAmount;
    }

    public void setCocktailsAmount(int cocktailsAmount) {
        this.cocktailsAmount = cocktailsAmount;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserStatDto that = (UserStatDto) o;

        if (userId != that.userId) return false;
        if (averageMark != that.averageMark) return false;
        if (cocktailsAmount != that.cocktailsAmount) return false;
        if (!login.equals(that.login)) return false;
        return role == that.role;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + login.hashCode();
        result = 31 * result + averageMark;
        result = 31 * result + cocktailsAmount;
        result = 31 * result + role.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "UserStatDto{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", averageMark=" + averageMark +
                ", cocktailsAmount=" + cocktailsAmount +
                ", role=" + role +
                '}';
    }
}
