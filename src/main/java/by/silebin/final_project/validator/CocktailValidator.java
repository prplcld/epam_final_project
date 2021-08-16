package by.silebin.final_project.validator;

public class CocktailValidator {

    private static final String NAME_REGEX = ".{3,45}";
    private static final String AMOUNT_REGEX = "\\d{1,4}";

    public static boolean validateName(String name) {
        return name.matches(NAME_REGEX);
    }

    public static boolean validateAmount(String amount) {
        return amount.matches(AMOUNT_REGEX);
    }
}
