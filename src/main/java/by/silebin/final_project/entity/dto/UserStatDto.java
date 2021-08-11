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
}
