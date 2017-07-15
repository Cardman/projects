package code.expressionlanguage.exceptions;
import code.expressionlanguage.opers.util.Struct;

public class InvokeRedinedMethException extends IndirectException {

    public InvokeRedinedMethException() {
    }

    public InvokeRedinedMethException(String _message, Struct _cause) {
        super(_message, _cause);
    }

    public InvokeRedinedMethException(String _message) {
        super(_message);
    }

    public InvokeRedinedMethException(Struct _cause) {
        super(_cause);
    }

}
