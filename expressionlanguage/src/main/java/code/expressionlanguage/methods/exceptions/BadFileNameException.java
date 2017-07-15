package code.expressionlanguage.methods.exceptions;

public class BadFileNameException extends RuntimeException {

    public BadFileNameException(String _className) {
        super(_className);
    }
}
