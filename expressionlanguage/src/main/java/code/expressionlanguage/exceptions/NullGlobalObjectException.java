package code.expressionlanguage.exceptions;
import code.util.exceptions.NullObjectException;

public class NullGlobalObjectException extends NullObjectException {

    public NullGlobalObjectException() {
    }

    public NullGlobalObjectException(String _message) {
        super(_message);
    }

}
