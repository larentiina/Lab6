package utilities;

import exceptions.*;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * the class starts the cyclic operation of the application
 */
public class Console {
    private final CommandManager commandManager;
    private final InputManager inputManager;


    public Console(InputManager inputManager,CommandManager commandManager) {
        this.commandManager = commandManager;
        commandManager.setConsole(this);
        this.inputManager = inputManager;
    }

    /**
     * The method starts the input to the console
     * @throws JAXBException
     * @throws IOException
     * @throws NoSuchCommandException if the command name is entered incorrectly
     */
    public void start() throws JAXBException, IOException, NoSuchCommandException, ScriptException {
        try {
            System.out.println("Введите команду");
            String command = inputManager.getScanner().nextLine();

            String[] parserCommand = inputManager.StringParser(command);

            String nameCommand = inputManager.nameCommand(parserCommand);
            commandManager.executeCommand(nameCommand);
        } catch (IllegalArgsException | IllegalValueException | IllegalKeyException | NoSuchCommandException e) {
            System.out.println(e.getMessage());
            this.start();
        } catch (ScriptException e) {
            System.out.println(e.getMessage());
            inputManager.setScanner(new Scanner(System.in));
            inputManager.setScriptMode(false);
            inputManager.setExecute(true);
            this.start();
        } catch (InterruptedIOException e) {
            System.out.println("Работа приложения прекращена из-за ctrl+c\nРабота приложения прекращена");
       }catch (NoSuchElementException e){
            System.out.println("Произошла непредвиденная ошибка");
        }
        }
    }

