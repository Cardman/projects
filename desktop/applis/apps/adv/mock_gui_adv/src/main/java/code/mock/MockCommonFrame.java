package code.mock;

import code.gui.GuiBaseUtil;
import code.gui.initialize.AbstractProgramInfos;

public final class MockCommonFrame extends MockAbsCommonFrame{
    public MockCommonFrame(AbstractProgramInfos _fr) {
        super(_fr, "");
    }

    public void pack() {
        GuiBaseUtil.recalculateWindow(this);
    }

}
