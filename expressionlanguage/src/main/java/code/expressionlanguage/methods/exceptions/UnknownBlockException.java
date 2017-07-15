package code.expressionlanguage.methods.exceptions;
import code.xml.RowCol;

public class UnknownBlockException extends RuntimeException {

    private final RowCol rc;

    public UnknownBlockException(String _message, RowCol _where) {
        super(_message);
        rc = _where;
    }

    public RowCol getRc() {
        return rc;
    }
}
