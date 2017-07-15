package code.expressionlanguage.methods.exceptions;

public class AlreadyDefinedVarException extends RuntimeException {

    public AlreadyDefinedVarException(String _message) {
        super(_message);
    }
}
