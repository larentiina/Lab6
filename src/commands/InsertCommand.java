package commands;

import data.Person;
import exceptions.IllegalArgsException;
import exceptions.IllegalKeyException;
import exceptions.IllegalValueException;
import utilities.InputManager;
import utilities.PersonCollection;
import utilities.PersonMaker;

/**
 * insert command
 */
public class InsertCommand extends Command{
    private final PersonCollection collection;
    private final InputManager inputManager;

    public InsertCommand(PersonCollection collection, InputManager inputManager) {
        this.collection = collection;
        this.inputManager = inputManager;
    }

    /**
     * add element to the collection with entered key
     * @throws IllegalKeyException if entered key is already exist
     * @throws IllegalArgsException if there are no arguments 1 and the type of the argument is not Integer
     * @throws IllegalValueException if the entered values of the element fields have incorrect types
     */
    @Override
    public void execute() throws IllegalKeyException, IllegalArgsException, IllegalValueException {
        try {
            int key = inputManager.parseArgInt();
            if(collection.getPersons().containsKey(key)){
                throw new IllegalKeyException("Элемент с таким ключом уже есть");
            }
            PersonMaker personMaker = new PersonMaker(collection, inputManager);
            Person person = personMaker.PersonMake(new Person());
            this.collection.addElement(key, person);
        }catch (IllegalKeyException | IllegalValueException e){
            throw e;
        }
    }

    @Override
    public String description() {
        return "insert null : добавить новый элемент с заданным ключом";
    }
}
