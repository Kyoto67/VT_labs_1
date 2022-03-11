//класс собственного непроверяемого исключения.
//вылетает, когда пытаются оседлать уже занятое облако.
public class CloudIsFullLoadException extends RuntimeException{
    public CloudIsFullLoadException (String ErrorMessage) {
        super(ErrorMessage);
    }
}
