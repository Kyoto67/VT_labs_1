package exceptions;

public class NonRealisticData extends Exception{
    public NonRealisticData(String errorMessage){
        super(errorMessage);
    }
}
