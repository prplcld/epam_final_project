package by.silebin.final_project.validator;

public class ParamValidator {
    private static final String INT_PARAM_REGEX = "\\d+";

    public static boolean validateIntParam(String param) {
        return param.matches(INT_PARAM_REGEX);
    }

    public static boolean validateBooleanParam(String param) {
        return param.equals("true") || param.equals("false");
    }
}
