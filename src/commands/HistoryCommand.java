package commands;

import exceptions.IllegalArgsException;
import utilities.CommandManager;
import utilities.InputManager;

/**
 * history command
 */
public class HistoryCommand extends Command{
    private final InputManager inputManager;
    private final CommandManager commandManager;


    public HistoryCommand(InputManager inputManager,CommandManager commandManager) {
        this.inputManager = inputManager;
        this.commandManager=commandManager;
    }

    /**
     * print a list of all commands that were entered earlier
     * @throws IllegalArgsException if arguments is not empty
     */
    @Override
    public void execute() throws IllegalArgsException {
        if(inputManager.getArguments().length==0) {
                for (String str : commandManager.getHistory()) {
                    System.out.println(str);

            }
        }else throw new IllegalArgsException("У команды history нет аргументов");
    }
    @Override
    public String description() {
        return "history: вывести последние" + CommandManager.NUMBER_COMMANDS_IN_HISTORY + "команд";
    }

}
