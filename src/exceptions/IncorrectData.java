package exceptions;

public class IncorrectData extends Exception{
    public IncorrectData(String errorMessage){
        super(errorMessage);
    }
}
