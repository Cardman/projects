package code.serialize.exceptions;

public class NoSuchDeclaredFieldException extends RuntimeException {

    public NoSuchDeclaredFieldException() {
    }

    public NoSuchDeclaredFieldException(String _message) {
        super(_message);
    }
}
