package by.silebin.final_project.command;

public final class PagePath {
    public static final String ERROR_PAGE = "error.jsp";
    public static final String NOT_FOUND_PAGE = "404.jsp";
    public static final String COCKTAIL_PAGE = "pages/cocktail.jsp";
    public static final String LIST_PAGE = "pages/list.jsp";
    public static final String LOGIN_PAGE = "pages/login-page.jsp";
    public static final String ADD_COCKTAIL_PAGE = "pages/add-cocktail.jsp";
    public static final String GO_TO_COCKTAIL_PAGE = "controller?command=cocktail_info&id=";
    public static final String PROFILE_PAGE = "pages/profile.jsp";
    public static final String REGISTER_PAGE = "pages/register.jsp";
    public static final String GO_TO_PROFILE = "controller?command=profile&id=";
    public static final String COCKTAIL_APPROVAL_PAGE = "pages/cocktail-approval.jsp";
    public static final String GO_TO_APPROVE_COCKTAILS = "controller?command=unapproved_cocktails";
    public static final String USER_STAT = "pages/userstat.jsp";
    public static final String EDIT_COCKTAIL = "pages/edit-cocktail.jsp";
    public static final String GO_TO_USER_STAT = "controller?command=users_stat";
    public static final String GO_TO_LOGIN = "controller?command=go_to_login";
    public static final String GO_TO_REGISTER = "controller?command=go_to_register";
    public static final String EDIT_USER = "pages/edit-user.jsp";
    public static final String GO_TO_ADD_COCKTAIL = "controller?command=go_to_add_cocktail";
    public static final String GO_TO_COCKTAILS_LIST = "controller?command=go_to_cocktails_list";
    public static final String GO_TO_EDIT_USER = "controller?command=edit_user&id=";
    public static final String GO_TO_EDIT_COCKTAIL = "controller?command=edit_cocktail&id=";
    public static final String DEFAULT = "controller?command=default";


    private PagePath() {
    }
}
