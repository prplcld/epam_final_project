package by.silebin.final_project.dao;

public final class ColumnName {

    //USERS TABLE
    public static final String ID = "id";
    public static final String USERS_LOGIN = "login";
    public static final String USERS_PASSWORD = "password";
    public static final String USERS_EMAIL = "email";
    public static final String USERS_ROLE_ID = "role_id";

    //COCKTAILS TABLE
    public static final String COCKTAILS_NAME = "name";
    public static final String COCKTAILS_DESCRIPTION = "description";
    public static final String COCKTAILS_USER_ID = "user_id";
    public static final String COCKTAILS_ICON = "icon";

    //INGREDIENTS TABLE
    public static final String INGREDIENTS_NAME = "name";
    public static final String INGREDIENTS_AMOUNT = "amount";
    public static final String INGREDIENTS_SCALE = "amount_scale";

    //COMMENTS TABLE
    public static final String COMMENTS_COMMENT = "text";
    public static final String COMMENTS_COCKTAIL_ID = "cocktail_id";
    public static final String COMMENTS_MARK = "mark";
    public static final String COMMENTS_USER_ID = "user_id";

    //

    private ColumnName() {}
}
