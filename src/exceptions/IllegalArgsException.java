package exceptions;

public class IllegalArgsException extends Exception{
    private final String message;
    public IllegalArgsException(String message) {
        this.message=message;
    }
    public String getMessage(){
        return message;
    }
}
