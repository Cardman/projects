package code.util.exceptions;

public class RuntimeClassNotFoundException extends RuntimeException {

    public RuntimeClassNotFoundException() {
    }
    public RuntimeClassNotFoundException(String _message) {
        super(_message);
    }
}
