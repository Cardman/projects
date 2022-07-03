package code.gui;

import code.util.StringMap;

public interface AbsGroupFrame {
    AbsCommonFrame getCommonFrame();
    String getApplicationName();
    boolean isOpened();
    StringMap<String> getMessages();
    void setMessages(StringMap<String> _messages);
    void changeLanguage(String _language);
}
