package code.gui.initialize;

import code.gui.AbsOtherDialog;
import code.gui.AbsOtherFrame;

public interface AbsLightFrameFactory {
    AbsOtherDialog newOtherDialog();
    AbsOtherFrame newOtherFrame();
    String paste();
    void copy(String _c);
}
