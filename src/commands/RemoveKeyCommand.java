package commands;

import exceptions.IllegalArgsException;
import exceptions.IllegalKeyException;
import utilities.InputManager;
import utilities.PersonCollection;

/**
 * remove_key command
 */
public class RemoveKeyCommand extends Command {
    private final PersonCollection collection;
    private final InputManager inputManager;

    public RemoveKeyCommand(PersonCollection collection, InputManager inputManager) {
        this.collection = collection;
        this.inputManager = inputManager;
    }

    /**
     * Deletes an element from the collection whose key is equal to the one entered
     * @throws IllegalKeyException if entered key does not exist
     * @throws IllegalArgsException if there are no arguments 1 and the type of the argument is not Integer
     */
    @Override
    public void execute() throws IllegalKeyException, IllegalArgsException {
        try {
            collection.RemoveElement(inputManager.parseArgInt());
        } catch (IllegalKeyException  e) {
            throw e;
        }
    }

    @Override
    public String description() {
        return "remove_key null: удалить элемент из коллекции по его ключу";
    }
}

