package utilities;

import exceptions.IllegalArgsException;
import exceptions.NoSuchCommandException;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;

/**
 * class that parsing the entered data
 */

public class InputManager {
    private CommandManager commandManager;
    private String[] arguments;
    private boolean scriptMode = false;
    private Path pathFile;
    private Scanner scanner=new Scanner(System.in);
    private boolean isExecute=true;
    private final int COMMAND_ARGS_INDEX=1;
    private final int COMMAND_ARG_INDEX_IN_ARGS=0;
    /**
     * separates the command name from the arguments
     * @param line must be string
     * @return array of entered words
     */
    public String[] StringParser(String line){
        String mas[]=line.split(" ");
        this.argCommand(mas);
        return mas;
    }

    /**
     * separates the command name from the entered arguments
     * @return name of command
     * @throws NoSuchCommandException if the command name is entered incorrectly
     */
    public String nameCommand(String mas[]) throws NoSuchCommandException {
        int COMMAND_NAME_INDEX = 0;
        if(commandManager.getCommands().containsValue(mas[COMMAND_NAME_INDEX])) {
            return mas[COMMAND_NAME_INDEX];
        } else throw new NoSuchCommandException();

    }

    /**
     * устанавливает введенные аргументы
     * @param mas
     */
    private void argCommand(String mas[]){
        String[] args = Arrays.copyOfRange(mas,COMMAND_ARGS_INDEX,mas.length);
        if(args.length!=0) {
            this.arguments = args;
        }else
            this.arguments=new String[]{};
    }

    public String[] getArguments()  {
            return arguments;
    }

    public void setArguments(String[] arguments) {
        this.arguments = arguments;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public void setCommandManager(CommandManager commandManager) {
        if(commandManager!=null){
        this.commandManager = commandManager;}
        else throw new NullPointerException();
    }

    /**
     * converts arguments to integer type
     * @return integer arguments
     * @throws IllegalArgsException if there is more than one argument and the argument is not converted to integer type
     */
    public Integer parseArgInt() throws IllegalArgsException {
        try {
            if(arguments.length!=1){
                throw new IllegalArgsException("У команды один аргумент");
            }
            return Integer.parseInt(this.getArguments()[COMMAND_ARG_INDEX_IN_ARGS]);

        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgsException("Аргумент должен иметь тип Integer");
        }
    }

    /**
     * converts arguments to string type
     * @return string arguments
     * @throws IllegalArgsException if if there is more than one argument
     */
    public String parseArgStr() throws IllegalArgsException {
            if(arguments.length!=1)
                throw new IllegalArgsException("У команды один аргумент");
        return this.getArguments()[COMMAND_ARG_INDEX_IN_ARGS];

    }

    /**
     * converts arguments to double  type
     * @return double arguments
     * @throws IllegalArgsException if there is more than one argument and the argument is not converted to double type
     */

    public Double parseArgDoub() throws IllegalArgsException {
        try {
            if(arguments.length!=1){
                throw new IllegalArgsException("У команды один аргумент");
            }
            return Double.parseDouble(this.getArguments()[COMMAND_ARG_INDEX_IN_ARGS]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException  e) {
            throw new IllegalArgsException("Аргумент должен иметь тип Integer");
        }
    }

    public boolean isScriptMode() {
        return scriptMode;
    }

    public void setScriptMode(boolean scriptMode) {
        this.scriptMode = scriptMode;
    }

    public Path getPathFile() {
        return pathFile;
    }

    public void setPathFile(Path pathFile) {

        this.pathFile = pathFile;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public boolean isExecute() {
        return isExecute;
    }

    public void setExecute(boolean execute) {
        isExecute = execute;
    }


}
