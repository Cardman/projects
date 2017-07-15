package code.expressionlanguage.exceptions;
import code.expressionlanguage.opers.util.Struct;

public abstract class IndirectException extends RuntimeException {

    private final Struct cause;

    public IndirectException() {
        cause = new Struct();
    }

    public IndirectException(String _message) {
        super(_message);
        cause = new Struct();
    }

    public IndirectException(Struct _cause) {
        super(getCause(_cause));
        cause = _cause;
    }

    public IndirectException(String _message, Struct _cause) {
        super(_message, getCause(_cause));
        cause = _cause;
    }

    private static Throwable getCause(Struct _cause) {
        if (_cause.getInstance() instanceof Throwable) {
            return (Throwable) _cause.getInstance();
        }
        return null;
    }

//    //This method is overridden only for debugging
//    @Override
//    public Throwable getCause() {
//        if (cause.getInstance() instanceof Throwable) {
//            return (Throwable) cause.getInstance();
//        }
//        return super.getCause();
//    }

    public Struct getCustCause() {
        return cause;
    }
}
