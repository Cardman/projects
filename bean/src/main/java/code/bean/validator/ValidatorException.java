/**
    */
package code.bean.validator;

/**
 */
public class ValidatorException extends RuntimeException {

    private static final String EMPTY_STRING = "";
    private final Message message;

    /**
    */
    public ValidatorException() {
        message = new Message();
        message.setMessage(EMPTY_STRING);
        message.setArgs(new Object[0]);
    }

    /**
    @param message
    */
    public ValidatorException(String _message) {
        super(_message);
        message = new Message();
        message.setMessage(_message);
        message.setArgs(new Object[0]);
    }

    /**
    @param _cause
    */
    public ValidatorException(Throwable _cause) {
        super(_cause);
        message = new Message();
        message.setMessage(EMPTY_STRING);
        message.setArgs(new Object[0]);
    }

    /**
    @param message
    @param _cause
    */
    public ValidatorException(String _message, Throwable _cause) {
        super(_message, _cause);
        message = new Message();
        message.setMessage(_message);
        message.setArgs(new Object[0]);
    }

    public ValidatorException(Message _message) {
        message = _message;
    }

    public String format() {
        return message.format();
    }

    public Object[] getArgs() {
        return message.getArgs();
    }
}
