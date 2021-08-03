package by.silebin.final_project.entity;

import java.util.Objects;

public class User {
    private int userId;
    private String login;
    private String email;
    private Role role;

    public User() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        User user = (User) o;
        return userId == user.userId && login.equals(user.login) && email.equals(user.email) && role == user.role;
    }

    @Override
    public int hashCode() {
        //FIXME
        int result = userId * login.hashCode() * email.hashCode();
        return result;
    }
}
