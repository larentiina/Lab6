package commands;

import exceptions.*;
import utilities.CommandManager;
import utilities.InputManager;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Stack;

/**
 * execute_script command
 */
public class ExecuteScriptCommand extends Command {
    private final InputManager inputManager;
    private final CommandManager commandManager;

    public ExecuteScriptCommand(InputManager inputManager, CommandManager commandManager) {
        this.inputManager = inputManager;
        this.commandManager = commandManager;
    }

    private final Stack<Scanner> historyCallsScript = new Stack<>();
    private final ArrayList<String> historyCallsFile = new ArrayList<>();

    /**
     * execute script file
     */
    @Override
    public void execute() throws JAXBException, IOException, NoSuchCommandException, IllegalArgsException, IllegalKeyException, ScriptException {
        try {
            inputManager.setPathFile(Path.of(inputManager.parseArgStr()));
            Path path = Paths.get(inputManager.parseArgStr());
            if(historyCallsFile.contains(inputManager.parseArgStr()))
            {
                throw new ScriptException("Вызов бесконечной рекурсии. Выполнение скрипта прервано");
            }
            else {
                historyCallsFile.add(inputManager.parseArgStr());
            }
            inputManager.setScriptMode(true);
            historyCallsScript.push(new Scanner(path).useDelimiter(System.getProperty("line.separator")));
                while (historyCallsScript.peek().hasNextLine()) {
                    inputManager.setScanner(historyCallsScript.peek());
                    String line = historyCallsScript.peek().nextLine();
                    String[] parserCommand = inputManager.StringParser(line);
                    String nameCommand = inputManager.nameCommand(parserCommand);
                    commandManager.executeCommand(nameCommand);

            }
            if (historyCallsScript.size() > 0) {
                historyCallsScript.pop();
            }
            if(historyCallsScript.size()==0) {
                inputManager.setScriptMode(false);
                inputManager.setExecute(true);
                inputManager.setScanner(new Scanner(System.in));
            }

        } catch (IllegalValueException | NoSuchCommandException | IllegalArgsException e) {
            throw new ScriptException("Ошибка в скрипте:"+ e.getMessage());
        } catch (NoSuchFileException e) {
            throw new ScriptException("Файл не найден.");
        } catch (NoSuchElementException e){
            throw new ScriptException("Произошла ошибка в скрипте. Проверьте правильно ли вы записали данные в него");
        }

    }


        @Override
        public String description () {
            return "execute_script file_name: считать и исполнить скрипт из указанного файла.";
        }

}
