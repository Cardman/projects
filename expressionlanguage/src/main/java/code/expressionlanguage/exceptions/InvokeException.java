package code.expressionlanguage.exceptions;
import code.expressionlanguage.opers.util.Struct;

public class InvokeException extends IndirectException {

    public InvokeException(String _message) {
        super(_message);
    }

    public InvokeException(Struct _cause) {
        super(_cause);
    }

    public InvokeException(String _message, Struct _cause) {
        super(_message, _cause);
    }
}
