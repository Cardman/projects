package code.expressionlanguage.exceptions;

public class DivideZeroException extends ArithmeticException {

    public DivideZeroException() {
    }

    public DivideZeroException(String _s) {
        super(_s);
    }

}
