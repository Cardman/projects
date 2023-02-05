package code.mock;

import code.gui.AbsCommonFrame;
import code.gui.AbsGroupFrame;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringMap;

public final class MockSampleFrame implements AbsGroupFrame {
    private final AbsCommonFrame frame;
    private StringMap<String> messages = new StringMap<String>();

    public MockSampleFrame(AbstractProgramInfos _fr) {
        frame = new MockCommonFrameTreeSample(_fr);
    }
    @Override
    public AbsCommonFrame getCommonFrame() {
        return frame;
    }

    @Override
    public String getApplicationName() {
        return "";
    }

    @Override
    public boolean isOpened() {
        return frame.isVisible();
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

    @Override
    public void init(AbstractProgramInfos _list) {
        _list.getCompoFactory();
    }

    @Override
    public void setByFirst(AbsGroupFrame _first) {
        messages = _first.getMessages();
    }
}
