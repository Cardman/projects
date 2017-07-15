/**
    */
package aiki.exceptions;

/**
    @author Cardman
 */
public class SimulationException extends RuntimeException {

    /**
    */
    public SimulationException() {
    }

    /**
    @param _message
    */
    public SimulationException(String _message) {
        super(_message);
    }

    /**
    @param _cause
    */
    public SimulationException(Throwable _cause) {
        super(_cause);
    }

    /**
    @param _message
    @param _cause
    */
    public SimulationException(String _message, Throwable _cause) {
        super(_message, _cause);
    }

    /**
    @param _message
    @param _cause
    @param _enableSuppression
    @param _writableStackTrace
    */
//    public SimulationException(String _message, Throwable _cause, boolean _enableSuppression, boolean _writableStackTrace) {
//        super(_message, _cause, _enableSuppression, _writableStackTrace);
//    }

}
