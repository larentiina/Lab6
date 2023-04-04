import exceptions.IllegalValueException;
import exceptions.NoSuchCommandException;
import exceptions.ScriptException;
import utilities.CommandManager;
import utilities.Console;
import utilities.InputManager;
import utilities.PersonCollection;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;

import static utilities.ParserXml.convertToHt;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchCommandException, ScriptException, IllegalValueException {
        try {


            File file = new File(System.getenv().get("FILE_NAME"));
            PersonCollection persons = new PersonCollection();
            persons.setCollection(convertToHt(file).getPersons());
            InputManager inputManager = new InputManager();
            CommandManager commandManager = new CommandManager(persons, inputManager);
            Console console = new Console(inputManager, commandManager);
            console.start();


        }
        catch (JAXBException | IllegalArgumentException e){
            System.out.println(e.getMessage());
            System.out.println("Приложение не может запуститься");
        }
    }

}

