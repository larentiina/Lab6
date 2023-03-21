package commands;

import exceptions.IllegalArgsException;
import utilities.InputManager;
import utilities.PersonCollection;

/**
 * команда sum_of_height
 */
public class SumOfHeight extends Command{
    private final PersonCollection collection;
    private final InputManager inputManager;

    public SumOfHeight(PersonCollection collection,InputManager inputManager) {
        this.collection = collection;
        this.inputManager=inputManager;
    }

    /**
     * print suom of height of elements of the collection
     * @throws IllegalArgsException if arguments is not empty
     */
    @Override
    public void execute() throws IllegalArgsException {
        if (inputManager.getArguments().length == 0) {
            System.out.println(collection.SumOfHeight());
        }else throw new IllegalArgsException("У команды sum_of_height нет аргументов");
    }

    @Override
    public String description() {
        return "sum_of_height: вывести сумму значений поля height для всех элементов коллекции";
    }

}
