package code.bean.validator;
import code.util.StringList;

/**
 */
public class Message {

    private String message;

    private Object[] args;

    private boolean formatMessage;

    public String format() {
        if (!formatMessage) {
            return message;
        }
        //It is enough here to interpret only {0}, {1}, ....
        return StringList.simpleFormat(message, args);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String _message) {
        message = _message;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object... _args) {
        args = _args;
    }

    public boolean isFormatMessage() {
        return formatMessage;
    }

    public void setFormatMessage(boolean _formatMessage) {
        formatMessage = _formatMessage;
    }
}
