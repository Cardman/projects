package code.expressionlanguage.methods.exceptions;

public class CyclicCallingException extends RuntimeException {

    public CyclicCallingException(String _message) {
        super(_message);
    }

}
