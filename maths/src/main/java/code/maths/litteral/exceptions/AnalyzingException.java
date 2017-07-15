package code.maths.litteral.exceptions;

public class AnalyzingException extends RuntimeException {

    public AnalyzingException() {
    }

    public AnalyzingException(String _message, Throwable _cause) {
        super(_message, _cause);
    }

    public AnalyzingException(String _message) {
        super(_message);
    }

    public AnalyzingException(Throwable _cause) {
        super(_cause);
    }

}
