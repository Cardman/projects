package code.expressionlanguage.exceptions;

public final class WrapperException extends RuntimeException {

    private final Throwable wrapped;

    public WrapperException(Throwable _wrapped) {
        wrapped = _wrapped;
    }

    public Throwable getWrapped() {
        return wrapped;
    }
}
