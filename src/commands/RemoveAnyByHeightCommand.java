package commands;

import data.Person;
import exceptions.IllegalArgsException;
import exceptions.IllegalKeyException;
import utilities.InputManager;
import utilities.PersonCollection;

import java.util.Map;

/**
 * remove_any_by_height command
 */
public class RemoveAnyByHeightCommand extends Command{
    private final InputManager inputManager;
    private final PersonCollection collection;

    public RemoveAnyByHeightCommand(InputManager inputManager, PersonCollection collection) {
        this.inputManager = inputManager;
        this.collection = collection;
    }

    /**
     * delete one element with the same height value
     * @throws IllegalArgsException if there are no arguments 1 and the type of the argument is not Double
     * @throws IllegalKeyException
     */
    @Override
    public void execute() throws IllegalArgsException, IllegalKeyException {
        boolean flag = false;
        for (Map.Entry<Integer, Person> entry : collection.getPersons().entrySet()) {
            Integer key = entry.getKey();
            Person person = entry.getValue();
            if (person.getHeight() == inputManager.parseArgDoub()) {
                collection.RemoveElement(key);
                flag=true;
                break;
            }
        }
            if (!flag) {
                throw new IllegalArgsException("Элемент с таким ростом отсутсвует");
            }

    }

    @Override
    public String description() {
        return "remove_any_by_height height: удалить из коллекции один элемент, значение поля height которого эквивалентно заданному";
    }

}

