package code.mock;

import code.gui.GuiBaseUtil;
import code.gui.initialize.AbstractProgramInfos;

public final class MockCommonFrame extends MockAbsCommonFrame{
    public MockCommonFrame(AbstractProgramInfos _fr, String _lg) {
        super(_fr, _lg);
    }

    public void pack() {
        GuiBaseUtil.recalculate(getPane());
    }

}
