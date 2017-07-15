package code.expressionlanguage.exceptions;

public class PrimitiveTypeException extends RuntimeException {

    public PrimitiveTypeException() {
    }

    public PrimitiveTypeException(String _message) {
        super(_message);
    }

    public PrimitiveTypeException(Throwable _cause) {
        super(_cause);
    }

    public PrimitiveTypeException(String _message, Throwable _cause) {
        super(_message, _cause);
    }

}
