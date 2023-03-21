package commands;

import exceptions.IllegalArgsException;
import utilities.InputManager;

/**
 * exit command
 */
public class ExitCommand extends Command{
    private final InputManager inputManager;

    public ExitCommand(InputManager inputManager) {
        this.inputManager = inputManager;
    }

    /**
     * completes the execution of the script or the application
     * @throws IllegalArgsException if arguments is not empty
     */
    @Override
    public void execute() throws IllegalArgsException {
        if(inputManager.getArguments().length==0) {
            inputManager.setExecute(false);
        }
        else throw new IllegalArgsException("У команды exit нет аргументов");
    }

    @Override
    public String description() {
        return "exit: завершить программу (без сохранения в файл)";
    }

}
