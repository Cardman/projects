package code.expressionlanguage.methods.exceptions;

public class BadVariableNameException extends RuntimeException {

    private static final String SEP = "\n";

    public BadVariableNameException(String _message, String _variable, String _where) {
        super(_where+SEP+_variable+SEP+_message);
    }
}
