package code.mock;

import code.gui.AbsCloseableDialog;
import code.gui.GuiBaseUtil;
import code.gui.initialize.AbstractProgramInfos;

public final class MockDialog extends MockAbsDialog{
    public MockDialog(AbstractProgramInfos _fr) {
        super(_fr);
    }

    public MockDialog(AbsCloseableDialog _cl, AbstractProgramInfos _fr) {
        super(_cl, _fr);
    }

    public void pack() {
        GuiBaseUtil.recalculate(getPane());
    }

}
