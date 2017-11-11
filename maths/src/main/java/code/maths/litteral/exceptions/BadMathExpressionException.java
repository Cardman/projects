package code.maths.litteral.exceptions;

public class BadMathExpressionException extends RuntimeException {

    public BadMathExpressionException() {
    }

    public BadMathExpressionException(String _message) {
        super(_message);
    }
}
