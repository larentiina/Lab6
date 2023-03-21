package commands;

import exceptions.IllegalArgsException;
import utilities.InputManager;
import utilities.PersonCollection;

/**
 * clear command
 */
public class ClearCommand extends Command{
    private final PersonCollection collection;
    private final InputManager inputManager;

    public ClearCommand(PersonCollection collection, InputManager inputManager) {
        this.collection = collection;
        this.inputManager = inputManager;
    }

    /**
     * remove all elements from collection
     * @throws IllegalArgsException if arguments is not empty
     */
    @Override
    public void execute() throws IllegalArgsException {
        if(inputManager.getArguments().length==0)
        collection.clearCollection();
        else throw new IllegalArgsException("У команды clear нет аргументов");
    }

    @Override
    public String description() {
        return "clear: очистить коллекцию";
    }

}
