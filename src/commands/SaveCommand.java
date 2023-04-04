package commands;

import exceptions.IllegalArgsException;
import utilities.InputManager;
import utilities.ParserXml;
import utilities.PersonCollection;

import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * save command
 */
public class SaveCommand extends Command{
    private final InputManager inputManager;
    private final PersonCollection collection;

    public SaveCommand(InputManager inputManager, PersonCollection collection) {
        this.inputManager = inputManager;
        this.collection = collection;
    }

    /**
     * save the collection in xml file
     * @throws JAXBException
     * @throws IOException
     * @throws IllegalArgsException if arguments is not empty
     */
    @Override
    public void execute() throws JAXBException, IOException, IllegalArgsException {
        if(inputManager.getArguments().length==0) {
            ParserXml.convertToXml(collection, System.getenv().get("FILE_NAME"));

        }
        else throw new IllegalArgsException("У команды save нет аргументов");

    }

    @Override
    public String description() {
        return "save: сохранить коллекцию в файл";
    }
}
