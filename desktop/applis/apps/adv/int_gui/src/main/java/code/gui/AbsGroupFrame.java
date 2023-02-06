package code.gui;

import code.gui.initialize.AbstractProgramInfos;
import code.util.StringMap;

public interface AbsGroupFrame {
    AbsCommonFrame getCommonFrame();
    String getApplicationName();
    boolean isOpened();
    StringMap<String> getMessages();
    void setMessages(StringMap<String> _messages);
    void changeLanguage(String _language);
    void dispatchExit();

    void init(AbstractProgramInfos _list);

    void setByFirst(AbsGroupFrame _first);
    void quit();
    boolean canChangeLanguage();
}
