package code.mock;

import code.gui.ChangeableTitle;
import code.gui.GuiBaseUtil;

public final class MockDialog extends MockAbsDialog implements ChangeableTitle {

    public void pack() {
        GuiBaseUtil.recalculateWindow(this);
    }

}
