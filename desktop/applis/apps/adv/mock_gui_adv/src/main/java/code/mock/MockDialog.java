package code.mock;

import code.gui.ChangeableTitle;
import code.gui.GuiBaseUtil;
import code.gui.initialize.AbstractProgramInfos;

public final class MockDialog extends MockAbsDialog implements ChangeableTitle {
    public MockDialog(AbstractProgramInfos _fr) {
        super(_fr);
    }

    public void pack() {
        GuiBaseUtil.recalculateWindow(this);
    }

}
