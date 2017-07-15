package code.expressionlanguage.exceptions;

public class UndefinedVariableException extends RuntimeException {

    private static final String SEP = "\n";

    public UndefinedVariableException(String _message, String _variable, String _where) {
        super(_where+SEP+_variable+SEP+_message);
    }
}
