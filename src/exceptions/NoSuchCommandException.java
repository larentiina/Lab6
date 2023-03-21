package exceptions;

public class NoSuchCommandException extends Exception{
    public String getMessage(){
        return "Имя команды введено неправильно";
    }
}
