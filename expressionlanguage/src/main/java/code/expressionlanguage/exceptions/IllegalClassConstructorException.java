package code.expressionlanguage.exceptions;

public class IllegalClassConstructorException extends RuntimeException {

    public IllegalClassConstructorException() {
    }

    public IllegalClassConstructorException(String _message) {
        super(_message);
    }

    public IllegalClassConstructorException(Throwable _cause) {
        super(_cause);
    }

    public IllegalClassConstructorException(String _message, Throwable _cause) {
        super(_message, _cause);
    }

}
