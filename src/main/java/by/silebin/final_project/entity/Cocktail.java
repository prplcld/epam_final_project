package by.silebin.final_project.entity;

import java.io.InputStream;

public class Cocktail {
    private int cocktailId;
    private String name;
    private String description;
    private transient InputStream icon;
    private int userId;
    private String base64Icon;
    private boolean approved;

    public Cocktail() {
    }

    public Cocktail(int cocktailId, String name, String description, InputStream icon, int userId) {
        this.cocktailId = cocktailId;
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.userId = userId;
    }

    public int getCocktailId() {
        return cocktailId;
    }

    public void setCocktailId(int cocktailId) {
        this.cocktailId = cocktailId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public InputStream getIcon() {
        return icon;
    }

    public void setIcon(InputStream icon) {
        this.icon = icon;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getBase64Icon() {
        return base64Icon;
    }

    public void setBase64Icon(String base64Icon) {
        this.base64Icon = base64Icon;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cocktail cocktail = (Cocktail) o;

        if (cocktailId != cocktail.cocktailId) return false;
        if (userId != cocktail.userId) return false;
        if (approved != cocktail.approved) return false;
        if (!name.equals(cocktail.name)) return false;
        return description.equals(cocktail.description);
    }

    @Override
    public int hashCode() {
        int result = cocktailId;
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + userId;
        result = 31 * result + (approved ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Cocktail{" +
                "cocktailId=" + cocktailId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", userId=" + userId +
                ", approved=" + approved +
                '}';
    }
}

