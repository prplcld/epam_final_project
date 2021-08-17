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

        if (ingredientId != that.ingredientId) return false;
        if (amount != that.amount) return false;
        if (!name.equals(that.name)) return false;
        return amountScale.equals(that.amountScale);
    }

    @Override
    public int hashCode() {
        int result = ingredientId;
        result = 31 * result + name.hashCode();
        result = 31 * result + amountScale.hashCode();
        result = 31 * result + amount;
        return result;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "ingredientId=" + ingredientId +
                ", name='" + name + '\'' +
                ", amountScale='" + amountScale + '\'' +
                ", amount=" + amount +
                '}';
    }
}
