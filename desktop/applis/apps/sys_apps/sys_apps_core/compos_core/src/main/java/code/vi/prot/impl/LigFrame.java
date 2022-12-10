package code.vi.prot.impl;

import code.gui.AbsOtherFrame;
import code.gui.ChangeableTitle;
import code.vi.prot.impl.gui.Panel;


public final class LigFrame extends LigWindow implements AbsOtherFrame, ChangeableTitle {

    private boolean mainFrame;

    public LigFrame() {
        super(Panel.newLineBox());
    }

    public void setMainFrame(boolean _mainFrame) {
        mainFrame = _mainFrame;
    }

    public boolean isMainFrame() {
        return mainFrame;
    }

}
