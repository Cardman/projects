package code.expressionlanguage.methods.exceptions;

public class CyclicInheritingException extends RuntimeException {

    public CyclicInheritingException(String _message) {
        super(_message);
    }

}
