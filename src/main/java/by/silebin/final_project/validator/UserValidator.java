package by.silebin.final_project.validator;

public class UserValidator {

    private static final String LOGIN_REGEX = "\\w{3,45}";
    private static final String EMAIL_REGEX = "^(?=.{3,30}$)[^\\s]+@[^\\s]+\\.[^\\s]+$";
    private static final int MAX_EMAIL_LENGTH = 45;

    public static boolean validateLogin(String login) {
        if(login.contains("<") || login.contains(">")) {
            return false;
        }
        return login.matches(LOGIN_REGEX);
    }

    public static boolean validateEmail(String email) {
        if (email.length() > MAX_EMAIL_LENGTH){
            return false;
        }
        return email.matches(EMAIL_REGEX);
    }
}
