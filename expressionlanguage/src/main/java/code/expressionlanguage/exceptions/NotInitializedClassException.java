package code.expressionlanguage.exceptions;


public class NotInitializedClassException extends RuntimeException {

    private final String className;

    public NotInitializedClassException(String _className) {
        className = _className;
    }

    public String getClassName() {
        return className;
    }
}
