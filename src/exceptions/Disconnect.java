package exceptions;


public class Disconnect extends Exception {
    public Disconnect(String ErrorMessage){
        super(ErrorMessage);
    }
}
