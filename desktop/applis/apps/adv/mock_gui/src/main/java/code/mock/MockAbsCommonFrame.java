package code.mock;

import code.gui.AbsCommonFrame;
import code.gui.AbsOtherFrame;
import code.gui.initialize.AbstractProgramInfos;

public abstract class MockAbsCommonFrame extends MockWindow implements AbsCommonFrame,AbsOtherFrame {

    protected MockAbsCommonFrame(AbstractProgramInfos _f, String _lgKey) {
        super(_f);
        setLanguageKey(_lgKey);
    }

    @Override
    public void setFocusableWindowState(boolean _b) {
        setFocusable(_b);
    }

    @Override
    public void setFocusable(boolean _b) {
        setVisible(isVisible());
    }


    @Override
    public void dispatchExit() {
        dispose();
    }

}
