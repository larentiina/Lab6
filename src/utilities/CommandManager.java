package utilities;

import commands.*;
import exceptions.*;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * class that generates and has map of command objects and calls them
 * class has a list of history entered command
 */
public class CommandManager {

    private final PersonCollection collection;
    private final InputManager inputManager;
    public final static int NUMBER_COMMANDS_IN_HISTORY = 7;
    private Console console;
    private final ArrayList<String> history = new ArrayList<>();
    public CommandManager(PersonCollection collection,InputManager inputManager) {
        this.collection = collection;
        this.inputManager=inputManager;
        inputManager.setCommandManager(this);
                commands.put(new InfoCommand(collection,inputManager), "info");
                commands.put(new ShowCommand(collection,inputManager), "show");
                commands.put(new ExitCommand(inputManager),"exit");
                commands.put(new InsertCommand(collection,inputManager), "insert");
                commands.put(new UpdateCommand(collection,inputManager),"update");
                commands.put(new RemoveKeyCommand(collection,inputManager),"remove_key");
                commands.put(new ClearCommand(collection,inputManager), "clear");
                commands.put(new SaveCommand(inputManager,collection),"save");
                commands.put(new HistoryCommand(inputManager,this),"history");
                commands.put(new ReplaceIfGreaterCommand(collection,inputManager),"replace_if_greater");
                commands.put(new ReplaceIfLoweCommand(collection,inputManager),"replace_if_lowe");
                commands.put(new RemoveAnyByHeightCommand(inputManager,collection),"remove_any_by_height");
                commands.put(new SumOfHeight(collection,inputManager),"sum_of_height");
                commands.put(new FilterGreaterThanLocation(collection,inputManager),"filter_greater_than_location");
                commands.put(new ExecuteScriptCommand(inputManager,this),"execute_script");
                commands.put(new HelpCommand(inputManager,this),"help");
    }

    private HashMap<Command,String> commands = new HashMap();

    /**
     * calls execution of commands by name
     * if command is executed her name go to list of history
     * @param name could be a string
     * @throws JAXBException
     * @throws IOException
     * @throws IllegalArgsException
     * @throws NoSuchCommandException if entered name of command does not exist
     * @throws IllegalValueException
     * @throws IllegalKeyException
     */
    public void executeCommand(String name) throws JAXBException, IOException, IllegalArgsException, NoSuchCommandException, IllegalValueException, IllegalKeyException, ScriptException {
            for (Map.Entry<Command, String> entry : commands.entrySet()) {
                if (entry.getValue().equals(name)) {
                    this.AddCommandInHistory(entry.getValue());
                    entry.getKey().execute();
                    if (inputManager.isExecute() && !inputManager.isScriptMode()) {
                        console.start();
                    }
                }
            }
    }

    public PersonCollection getCollection() {
        return collection;
    }

    public Console getConsole() {
        return console;
    }

    public void setConsole(Console console) {
        if(console!=null)
        this.console = console;
        else throw new NullPointerException();
    }

    public InputManager getInputManager() {
        return inputManager;
    }

    public HashMap<Command, String> getCommands() {
        return commands;
    }
    public ArrayList<String> getHistory() {
        return history;
    }
    public void AddCommandInHistory(String command){
        history.add(command);
        if(history.size()>NUMBER_COMMANDS_IN_HISTORY)
            history.remove(0);
    }
}


