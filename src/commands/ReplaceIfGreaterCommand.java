package commands;

import data.Person;
import exceptions.IllegalArgsException;
import exceptions.IllegalKeyException;
import exceptions.IllegalValueException;
import utilities.InputManager;
import utilities.PersonCollection;
import utilities.PersonMaker;

/**
 * replace_if_greater_command
 */
public class ReplaceIfGreaterCommand extends Command{
    private final PersonCollection collection;
    private final InputManager inputManager;

    public ReplaceIfGreaterCommand(PersonCollection collection, InputManager inputManager) {
        this.collection= collection;
        this.inputManager = inputManager;
    }

    /**
     * replaces an element by the entered key if the entered element is larger than the original one
     * @throws IllegalArgsException if there are no arguments 1 and the type of the argument is not Integer
     * @throws IllegalValueException if the entered values of the element fields have incorrect types
     * @throws IllegalKeyException if entered key does not exist
     */
    @Override
    public void execute() throws IllegalArgsException, IllegalValueException, IllegalKeyException {
        try {
            int key = inputManager.parseArgInt();
            if (!collection.getPersons().containsKey(key)) {
                throw new IllegalKeyException("Элемент с таким ключом отсутствует");
            }
            PersonMaker personMaker = new PersonMaker(collection, inputManager);
            Person person = personMaker.PersonMake(new Person());
            if (person.compareTo(collection.getPersons().get(inputManager.parseArgInt())) > 0) {
                collection.Replace(key, person);
            }
        }catch (IllegalKeyException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String description() {
        return "replace_if_greater null: заменить значение по ключу, если новое значение больше старого";
    }

}
