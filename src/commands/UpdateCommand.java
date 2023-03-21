package commands;

import exceptions.IllegalArgsException;
import exceptions.IllegalValueException;
import utilities.InputManager;
import utilities.PersonCollection;
import utilities.PersonMaker;

/**
 * update command
 */
public class UpdateCommand extends Command{
    private final PersonCollection collection;
    private final InputManager inputManager;
    public UpdateCommand(PersonCollection collection, InputManager inputManager) {
        this.collection = collection;
        this.inputManager = inputManager;
    }

    /**
     * updates the element by the entered id
     * @throws IllegalArgsException if there are no arguments 1 and the type of the argument is not Integer, element with such id does not exist
     * @throws IllegalValueException if the entered values of the element fields have incorrect types
     */
    @Override
    public void execute() throws IllegalArgsException, IllegalValueException {
        try {
            PersonMaker personMaker = new PersonMaker(collection, inputManager);
           personMaker.PersonMake(collection.searchById(inputManager.parseArgInt()));

        }catch(NullPointerException e){
            throw new IllegalArgsException("Такого id не существует");
        }catch (IllegalValueException e){
            throw e;
        }
    }

    @Override
    public String description() {
        return "update id : обновить значение элемента коллекции, id которого равен заданному";
    }
}
