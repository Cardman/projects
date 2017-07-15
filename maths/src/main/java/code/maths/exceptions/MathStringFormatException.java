package code.maths.exceptions;

public class MathStringFormatException extends RuntimeException {

    private static final String SEPARATOR = "\n";

    public MathStringFormatException() {
    }

    public MathStringFormatException(String _message, int _index) {
        super(_index+SEPARATOR+_message);
    }

}
