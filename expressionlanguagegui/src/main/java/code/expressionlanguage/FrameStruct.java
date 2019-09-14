package code.expressionlanguage;

import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.GroupFrame;
import code.gui.WithListener;

import java.awt.event.WindowListener;

public final class FrameStruct extends WindowStruct {
    private GroupFrame commonFrame;
    public FrameStruct(GroupFrame _frame) {
        commonFrame = _frame;
    }

    @Override
    public String getClassName(ExecutableCode _contextEl) {
        return ((LgNamesGui) _contextEl.getContextEl().getStandards()).getAliasFrame();
    }

    @Override
    protected WithListener getAbstractWindow() {
        return getCommonFrame();
    }

    @Override
    public void pack() {
        getCommonFrame().pack();
    }

    public GroupFrame getCommonFrame() {
        return commonFrame;
    }

    public void dispose() {
        commonFrame.dispose();
    }
}
