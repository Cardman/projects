package aiki.comments;
import aiki.DataBase;
import code.util.StringList;

public class Comment {

    private static final String RETURN_LINE = "\n";
    private StringList messages = new StringList();

    public void addComment(Comment _comment) {
        messages.addAllElts(_comment.messages);
    }
    
    public void addMessage(String _messageFormat, String... _args) {
        try {
            messages.add(StringList.simpleStringsFormat(_messageFormat, _args));
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
