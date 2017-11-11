package code.expressionlanguage.exceptions;

public class IllegalClassConstructorException extends RuntimeException {

    public IllegalClassConstructorException() {
    }

    public IllegalClassConstructorException(String _message) {
        super(_message);
    }
}
