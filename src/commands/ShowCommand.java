package commands;

import data.Person;
import exceptions.IllegalArgsException;
import utilities.InputManager;
import utilities.PersonCollection;

/**
 * команда show
 */
public class ShowCommand extends Command {
    private final PersonCollection collection;
    private final InputManager inputManager;

    public ShowCommand(PersonCollection collection, InputManager inputManager) {

        this.collection = collection;
        this.inputManager=inputManager;
    }

    /**
     * print elements of collection
     * @throws IllegalArgsException if arguments is not empty
     */
    @Override
    public void execute() throws IllegalArgsException {
        if (inputManager.getArguments().length == 0) {
            if(collection.getPersons().size()!=0){
            for (Person p :
                    collection.getPersons().values()) {
                System.out.println(p.toString());
            }}
            else{
                System.out.println("{}");
            }
        } else throw new IllegalArgsException("У команды show нет аргументов");
    }

    @Override
    public String description() {
        return "show: вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}
