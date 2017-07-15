package code.expressionlanguage.exceptions;
import code.util.exceptions.NullObjectException;

public class UnwrappingException extends NullObjectException {

    public UnwrappingException() {
    }

    public UnwrappingException(String _message) {
        super(_message);
    }

}
