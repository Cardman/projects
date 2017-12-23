package code.expressionlanguage.methods.exceptions;
import code.expressionlanguage.methods.util.ErrorList;

public class AnalyzingErrorsException extends RuntimeException {

    private static final String SEP_ERRORS = "\n";

    public AnalyzingErrorsException() {
    }

    public AnalyzingErrorsException(ErrorList _errors) {
        super(_errors.display());
    }


    public AnalyzingErrorsException(String _message) {
        super(_message);
    }
}
