package code.serialize.exceptions;

public class NoSuchDeclaredConstructorException extends RuntimeException {

    public NoSuchDeclaredConstructorException() {
    }

    public NoSuchDeclaredConstructorException(String _message) {
        super(_message);
    }

    public NoSuchDeclaredConstructorException(Throwable _t) {
        super(_t);
    }
}
