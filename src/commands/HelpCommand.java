package commands;

import exceptions.IllegalArgsException;
import utilities.CommandManager;
import utilities.InputManager;

/**
 * help command
 */
public class HelpCommand extends Command{
    private final InputManager inputManager;
    private final CommandManager commandManager;

    public HelpCommand(InputManager inputManager,CommandManager commandManager) {
        this.inputManager = inputManager;
        this.commandManager=commandManager;
    }

    /**
     * print all commands with their names and descriptions
     * @throws IllegalArgsException if arguments is not empty
     */

    @Override
    public void execute() throws IllegalArgsException {
        if(inputManager.getArguments().length==0) {
            for (Command command:commandManager.getCommands().keySet()) {
                System.out.println(command.description());
            }

        } else throw new IllegalArgsException("У команды help нет аргументов");
    }

    @Override
    public String description() {
        return "help: вывести справку по доступным командам";
    }

}
