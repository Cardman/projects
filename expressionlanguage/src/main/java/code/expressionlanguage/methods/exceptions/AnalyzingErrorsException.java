package code.expressionlanguage.methods.exceptions;
import code.expressionlanguage.methods.util.FoundErrorInterpret;
import code.util.CustList;

public class AnalyzingErrorsException extends RuntimeException {

    private static final String SEP_ERRORS = "\n";

    public AnalyzingErrorsException() {
    }

    public AnalyzingErrorsException(CustList<FoundErrorInterpret> _errors) {
        super(_errors.join(SEP_ERRORS));
    }


    public AnalyzingErrorsException(String _message) {
        super(_message);
    }
}
