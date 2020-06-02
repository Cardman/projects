package aiki.comments;
import aiki.db.DataBase;
import code.util.StringList;

public class Comment {

    private StringList messages = new StringList();

    public void addComment(Comment _comment) {
        messages.addAllElts(_comment.messages);
    }
    
    public void addMessage(String _messageFormat, String... _args) {
        if (_messageFormat == null) {
            messages.add(DataBase.EMPTY_STRING);
            return;
        }
        messages.add(StringList.simpleStringsFormat(_messageFormat, _args));
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

    public String join() {
        return StringList.join(messages, DataBase.EMPTY_STRING);
    }
}
