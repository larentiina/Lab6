package commands;

import exceptions.*;

import javax.xml.bind.JAXBException;
import java.io.IOException;
public abstract class Command {

    public abstract void execute() throws JAXBException, IOException, NoSuchCommandException, IllegalKeyException, IllegalArgsException, IllegalValueException, ScriptException;
    public abstract String description();


}
