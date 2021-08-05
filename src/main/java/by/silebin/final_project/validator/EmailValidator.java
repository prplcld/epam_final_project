package by.silebin.final_project.validator;

public class EmailValidator {
    private static final String EMAIL_REGEX = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";

    private EmailValidator() {
    }

    public static boolean isValid(String email) {
        return email.matches(EMAIL_REGEX);
    }
}
