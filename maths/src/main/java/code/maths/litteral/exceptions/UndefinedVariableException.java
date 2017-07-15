package code.maths.litteral.exceptions;

public class UndefinedVariableException extends RuntimeException {

    private static final String SEP = "\n";

    public UndefinedVariableException(String _message, String _where) {
        super(_where+SEP+_message);
    }
}
