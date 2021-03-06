package by.silebin.final_project.command;

import by.silebin.final_project.command.impl.*;
import by.silebin.final_project.command.impl.go.GoToAddCocktailPageCommand;
import by.silebin.final_project.command.impl.go.GoToCocktailsListCommand;
import by.silebin.final_project.command.impl.go.GoToLoginPageCommand;
import by.silebin.final_project.command.impl.go.GoToRegisterPageCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private static CommandProvider instance;
    private final Map<CommandType, Command> commands = new HashMap<>();

    private CommandProvider() {
        commands.put(CommandType.GO_TO_COCKTAILS_LIST, new GoToCocktailsListCommand());
        commands.put(CommandType.GO_TO_LOGIN, new GoToLoginPageCommand());
        commands.put(CommandType.DEFAULT, new DefaultCommand());
        commands.put(CommandType.LOGIN_USER, new LoginUserCommand());
        commands.put(CommandType.COCKTAIL_INFO, new CocktailInfoCommand());
        commands.put(CommandType.GO_TO_ADD_COCKTAIL, new GoToAddCocktailPageCommand());
        commands.put(CommandType.ADD_COCKTAIL, new AddCocktailCommand());
        commands.put(CommandType.LOGOUT_USER, new LogoutUserCommand());
        commands.put(CommandType.LEAVE_COMMENT, new LeaveCommentCommand());
        commands.put(CommandType.PROFILE, new ProfileCommand());
        commands.put(CommandType.GO_TO_REGISTER, new GoToRegisterPageCommand());
        commands.put(CommandType.REGISTER_USER, new RegisterUserCommand());
        commands.put(CommandType.RATE_USER, new RateUserCommand());
        commands.put(CommandType.CHANGE_LOCALE, new ChangeLocaleCommand());
        commands.put(CommandType.UNAPPROVED_COCKTAILS, new UnapprovedCocktailsCommand());
        commands.put(CommandType.APPROVE_COCKTAIL, new ApproveCocktailCommand());
        commands.put(CommandType.DELETE_COMMENT, new DeleteCommentCommand());
        commands.put(CommandType.USERS_STAT, new UsersStatCommand());
        commands.put(CommandType.DELETE_COCKTAIL, new DeleteCocktailCommand());
        commands.put(CommandType.EDIT_COCKTAIL, new EditCocktailCommand());
        commands.put(CommandType.EDIT_USER, new EditUserCommand());
        commands.put(CommandType.CHANGE_USER_ROLE, new ChangeUserRoleCommand());
        commands.put(CommandType.SAVE_COCKTAIL, new SaveCocktailCommand());
        commands.put(CommandType.SAVE_USER, new SaveUserCommand());
    }

    public static CommandProvider getInstance() {
        if (instance == null) {
            instance = new CommandProvider();
        }
        return instance;
    }


    public Command getCommand(String commandName) {
        if (commandName == null) {
            return commands.get(CommandType.DEFAULT);
        }
        CommandType commandType;
        try {
            commandType = CommandType.valueOf(commandName.toUpperCase());
        } catch (IllegalArgumentException e) {
            commandType = CommandType.DEFAULT;
        }
        return commands.get(commandType);
    }
}
