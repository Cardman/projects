package code.mock;

import code.gui.GuiBaseUtil;

public final class MockCommonFrame extends MockAbsCommonFrame{
    public MockCommonFrame() {
        super("");
    }

    public void pack() {
        GuiBaseUtil.recalculateWindow(this);
    }

}
