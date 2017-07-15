package code.maths.exceptions;

public class NegativeNumberException extends RuntimeException {

    public NegativeNumberException() {
    }

    public NegativeNumberException(String _message) {
        super(_message);
    }
}
