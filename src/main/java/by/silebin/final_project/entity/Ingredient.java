package by.silebin.final_project.entity;

public class Ingredient {
    private int ingredientId;
    private String name;
    private String amountScale;
    transient private int amount;

    public Ingredient() {
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmountScale() {
        return amountScale;
    }

    public void setAmountScale(String amountScale) {
        this.amountScale = amountScale;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return ingredientId == that.ingredientId && name.equals(that.name) && amountScale.equals(that.amountScale);
    }

    @Override
    public int hashCode() {
        int result = ingredientId * name.hashCode() * amountScale.hashCode();
        return result;
    }
}
