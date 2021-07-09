package by.silebin.final_project.command;

import by.silebin.final_project.command.impl.GetCocktailsListCommand;
import by.silebin.final_project.command.impl.go.GoToLoginPageCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private static CommandProvider instance;
    private final Map<CommandType, Command> commands = new HashMap<>();


    private CommandProvider() {
        commands.put(CommandType.GO_TO_LIST_PAGE, new GetCocktailsListCommand());
        commands.put(CommandType.GO_TO_LOGIN, new GoToLoginPageCommand());
        commands.put(CommandType.DEFAULT, new GetCocktailsListCommand());
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
