package code.expressionlanguage.methods.exceptions;

public class BadConstructorCall extends RuntimeException {

    public BadConstructorCall() {
    }

    public BadConstructorCall(String _message) {
        super(_message);
    }

}
