package commands;

import exceptions.IllegalArgsException;
import utilities.InputManager;
import utilities.PersonCollection;

/**
 * info command
 */
public class InfoCommand extends Command{
    private final PersonCollection collection;
    private final InputManager inputManager;
    public InfoCommand(PersonCollection collection, InputManager inputManager) {
        this.inputManager=inputManager;
        this.collection=collection;
    }

    /**
     * print info about collection: type, size, data inizialisation
     * @throws IllegalArgsException if arguments is not empty
     */
    @Override
    public void execute() throws IllegalArgsException {
        if(inputManager.getArguments().length==0){
        System.out.println("Type: " + collection.getPersons().getClass()
                + "\nSize:" + collection.getPersons().size()
                + "\nData inizialisation: " + collection.getDateOfInitialization());}
        else throw new IllegalArgsException("У команды info нет аргументов");
    }

    @Override
    public String description() {
        return "info: вывести в стандартный поток вывода информацию о коллекции";
    }
}
