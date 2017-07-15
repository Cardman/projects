package code.formathtml.exceptions;
import code.expressionlanguage.exceptions.IndirectException;
import code.expressionlanguage.opers.util.Struct;

public class GettingKeysException extends IndirectException {

    public GettingKeysException() {
    }

    public GettingKeysException(String _message) {
        super(_message);
    }

    public GettingKeysException(Struct _cause) {
        super(_cause);
    }

    public GettingKeysException(String _message, Struct _cause) {
        super(_message, _cause);
    }

}
