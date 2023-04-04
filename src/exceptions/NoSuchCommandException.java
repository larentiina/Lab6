package exceptions;

public class NoSuchCommandException extends Exception{
    public String getMessage(){
        return "имя команды введено неправильно";
    }
}
