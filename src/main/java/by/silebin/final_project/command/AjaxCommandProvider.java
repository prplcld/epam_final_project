package by.silebin.final_project.command;

import by.silebin.final_project.command.impl.ajax.*;

import java.util.HashMap;
import java.util.Map;

public class AjaxCommandProvider {
    private static AjaxCommandProvider instance;
    private final Map<AjaxCommandType, AjaxCommand> commands = new HashMap<>();

    private AjaxCommandProvider() {
        commands.put(AjaxCommandType.GET_COCKTAILS, new GetCocktailsAjaxCommand());
        commands.put(AjaxCommandType.GET_COCKTAILS_AMOUNT, new GetCocktailsAmountAjaxCommand());
        commands.put(AjaxCommandType.SEARCH, new SearchCocktailsCommand());
        commands.put(AjaxCommandType.GET_INGREDIENTS, new GetIngredientsAjaxCommand());
        commands.put(AjaxCommandType.DEFAULT, new DefaultAjaxCommand());
    }

    public static AjaxCommandProvider getInstance() {
        if (instance == null) {
            instance = new AjaxCommandProvider();
        }
        return instance;
    }

    public AjaxCommand getCommand(String commandName) {

        if (commandName == null) {
            return commands.get(AjaxCommandType.DEFAULT);
        }
        AjaxCommandType commandType;
        try {
            commandType = AjaxCommandType.valueOf(commandName.toUpperCase());
        } catch (IllegalArgumentException e) {
            commandType = AjaxCommandType.DEFAULT;
        }
        return commands.get(commandType);
    }
}
