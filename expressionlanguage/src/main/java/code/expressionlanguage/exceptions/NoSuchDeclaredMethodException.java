package code.expressionlanguage.exceptions;

public class NoSuchDeclaredMethodException extends RuntimeException {

    public NoSuchDeclaredMethodException() {
    }

    public NoSuchDeclaredMethodException(String _message) {
        super(_message);
    }
}
