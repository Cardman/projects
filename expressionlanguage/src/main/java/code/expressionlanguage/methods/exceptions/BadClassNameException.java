package code.expressionlanguage.methods.exceptions;

public class BadClassNameException extends RuntimeException {

    public BadClassNameException(String _className) {
        super(_className);
    }
}
