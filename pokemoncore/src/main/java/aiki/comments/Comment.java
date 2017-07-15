package aiki.comments;
import code.util.StringList;
import aiki.DataBase;

public class Comment {

    private static final String RETURN_LINE = "\n";
    private StringList messages = new StringList();

    public void addComment(Comment _comment) {
        messages.addAllElts(_comment.messages);
    }

    public void addMessage(String _messageFormat, Object... _args) {
        try {
//            messages.add(MessageFormat.format(_messageFormat, _args));
            messages.add(StringList.simpleFormat(_messageFormat, _args));
        } catch (RuntimeException _0) {
            messages.add(DataBase.EMPTY_STRING);
        }
    }

    public void clearMessages() {
        messages.clear();
    }

    public void addEmptyMessage() {
        messages.add(DataBase.EMPTY_STRING);
    }

    public StringList getMessages() {
        return messages;
    }

    public String joinLines() {
        return messages.join(RETURN_LINE);
    }

    public String join() {
        return messages.join(DataBase.EMPTY_STRING);
    }
}
