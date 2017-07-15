package code.expressionlanguage.exceptions;

public class AbstractClassConstructorException extends RuntimeException {

    public AbstractClassConstructorException() {
    }

    public AbstractClassConstructorException(String _message) {
        super(_message);
    }

    public AbstractClassConstructorException(Throwable _cause) {
        super(_cause);
    }

    public AbstractClassConstructorException(String _message, Throwable _cause) {
        super(_message, _cause);
    }

}
