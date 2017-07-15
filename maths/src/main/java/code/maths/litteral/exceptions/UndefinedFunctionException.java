package code.maths.litteral.exceptions;

public class UndefinedFunctionException extends RuntimeException {

    private static final String SEP = "\n";

    public UndefinedFunctionException(String _message, String _variable) {
        super(_variable+SEP+_message);
    }
}
