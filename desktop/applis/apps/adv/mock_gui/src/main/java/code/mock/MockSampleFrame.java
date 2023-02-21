package code.mock;

import code.gui.AbsCommonFrame;
import code.gui.AbsGroupFrame;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringMap;

public final class MockSampleFrame implements AbsGroupFrame {
    private final AbsCommonFrame frame;
    private StringMap<String> messages = new StringMap<String>();
//    private boolean changeable;

    public MockSampleFrame(AbstractProgramInfos _fr) {
        frame = new MockCommonFrameTreeSample(_fr);
    }
    @Override
    public AbsCommonFrame getCommonFrame() {
        return frame;
    }

    @Override
    public void quit() {
        frame.setVisible(false);
    }

    @Override
    public String getApplicationName() {
        return "";
    }

    @Override
    public StringMap<String> getMessages() {
        return messages;
    }

    @Override
    public void setMessages(StringMap<String> _m) {
        messages = _m;
    }

    @Override
    public void changeLanguage(String _language) {
        frame.setTitle(_language);
    }

    @Override
    public void dispatchExit() {
        frame.dispatchExit();
    }

    public void init(AbstractProgramInfos _list) {
        _list.getCompoFactory();
    }

    public void setByFirst(AbsGroupFrame _first) {
        messages = _first.getMessages();
    }

//    @Override
//    public boolean canChangeLanguage() {
//        return changeable;
//    }

//    public void setChangeable(boolean _ch) {
//        this.changeable = _ch;
//    }
}
