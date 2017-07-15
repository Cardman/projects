package code.maths.litteral.exceptions;

public class BadMathExpressionException extends RuntimeException {

    public BadMathExpressionException() {
    }

    public BadMathExpressionException(String _message) {
        super(_message);
    }

    public BadMathExpressionException(Throwable _cause) {
        super(_cause);
    }

    public BadMathExpressionException(String _message, Throwable _cause) {
        super(_message, _cause);
    }

}
