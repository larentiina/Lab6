package commands;

import data.Location;
import data.Person;
import exceptions.IllegalArgsException;
import exceptions.IllegalValueException;
import utilities.InputManager;
import utilities.PersonCollection;
import utilities.PersonMaker;

/**
 * filter_greater_than_location command
 */
public class FilterGreaterThanLocation extends Command{
    private final PersonCollection collection;
    private final InputManager inputManager;

    public FilterGreaterThanLocation(PersonCollection collection, InputManager inputManager) {
        this.collection = collection;
        this.inputManager = inputManager;
    }

    /**
     * Displays objects of the location type that are larger than the one entered
     * @throws IllegalArgsException если аргументов больше 0
     */
    @Override
    public void execute() throws IllegalArgsException, IllegalValueException {
        if(inputManager.getArguments().length==0) {
            PersonMaker personMaker = new PersonMaker(collection, inputManager);
            Location location = personMaker.makeLocation(inputManager.getScanner(), new Location());
            if(location==null){
                throw new IllegalValueException("Сравнение с объектом null невозможно");
            }
            for (Person p : collection.getPersons().values()) {
                if(p.getLocation()==null){
                    continue;
                }
                if (p.getLocation().compareTo(location) > 0) {
                    System.out.println(p.getLocation().toString());
                }
            }
        } else throw new IllegalArgsException("У команды filter_greater_than_location нет аргументов");
    }

    @Override
    public String description() {
        return "filter_greater_than_location : вывести элементы, значение поля location которых больше заданного";
    }

}
