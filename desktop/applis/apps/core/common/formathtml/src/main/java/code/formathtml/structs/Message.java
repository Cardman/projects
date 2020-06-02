package code.formathtml.structs;
import code.util.StringList;

/**
 */
public class Message {

    private static final String EMPTY_STRING = "";

    private String message;

    private String[] args;

    public static Message newStandardMessage() {
        return newStandardMessage(EMPTY_STRING);
    }

    public static Message newStandardMessage(String _message) {
        Message message_ = new Message();
        message_.setMessage(_message);
        message_.setArgs();
        return message_;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String _message) {
        message = _message;
    }

    public StringList getArgs() {
        return new StringList(args);
    }

    public void setArgs(String... _args) {
        args = _args;
    }

}
