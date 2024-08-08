package code.mock;

import code.gui.AbsCommonFrame;
import code.gui.AbsGroupFrame;
import code.gui.AbsOpenQuit;
import code.gui.initialize.AbstractProgramInfos;

public final class MockSampleFrame implements AbsGroupFrame, AbsOpenQuit {
    private final AbsCommonFrame frame;
//    private StringMap<String> messages = new StringMap<String>();
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

//    @Override
//    public boolean canChangeLanguage() {
//        return changeable;
//    }

//    public void setChangeable(boolean _ch) {
//        this.changeable = _ch;
//    }
}
