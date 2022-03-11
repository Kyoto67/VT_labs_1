//класс собственного проверяемого исключения.
//выпадает в случае сбоя в управлении облаком.
public class BrokenCloudControlException extends Exception{
    public BrokenCloudControlException(String ErrorMessage){
        super(ErrorMessage);
    }
}
