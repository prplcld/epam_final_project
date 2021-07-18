package by.silebin.final_project.entity;

import java.io.InputStream;
import java.util.Objects;

public class Cocktail {
    private int cocktailId;
    private String name;
    private String description;
    private transient InputStream icon;
    private int userId;
    private String base64Icon;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cocktail cocktail = (Cocktail) o;
        return cocktailId == cocktail.cocktailId && userId == cocktail.userId && name.equals(cocktail.name) && description.equals(cocktail.description);
    }

    @Override
    public int hashCode() {
        //FIXME
        return Objects.hash(cocktailId, name, description, userId);
    }
}
