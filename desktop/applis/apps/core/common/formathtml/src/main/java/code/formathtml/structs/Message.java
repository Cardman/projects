package code.formathtml.structs;
import code.util.StringList;

/**
 */
public final class Message {

    private static final String EMPTY_STRING = "";

    private String content;

    private String[] args;

    public static Message newStandardMessage() {
        return newStandardMessage(EMPTY_STRING);
    }

    public static Message newStandardMessage(String _message) {
        Message message_ = new Message();
        message_.setContent(_message);
        message_.setArgs();
        return message_;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String _message) {
        content = _message;
    }

    public StringList getArgs() {
        return new StringList(args);
    }

    public void setArgs(String... _args) {
        args = _args;
    }

}
