/**
    */
package aiki.exceptions;

/**
    @author Cardman
 */
public class DataException extends RuntimeException {

    /**
    */
    public DataException() {
    }

    /**
    @param _message
    */
    public DataException(String _message) {
        super(_message);
    }

    /**
    @param _cause
    */
    public DataException(Throwable _cause) {
        super(_cause);
    }

    /**
    @param _message
    @param _cause
    */
    public DataException(String _message, Throwable _cause) {
        super(_message, _cause);
    }

    /**
    @param _message
    @param _cause
    @param _enableSuppression
    @param _writableStackTrace
    */
//    public DataException(String _message, Throwable _cause, boolean _enableSuppression, boolean _writableStackTrace) {
//        super(_message, _cause, _enableSuppression, _writableStackTrace);
//    }

}
