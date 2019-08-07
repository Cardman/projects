package code.expressionlanguage;

import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.GroupFrame;

import java.awt.event.WindowListener;

public final class FrameStruct implements Struct {
    private GroupFrame commonFrame;
    public FrameStruct(GroupFrame _frame) {
        commonFrame = _frame;
    }
    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }

    @Override
    public String getClassName(ExecutableCode _contextEl) {
        return ((LgNamesGui) _contextEl.getContextEl().getStandards()).getAliasFrame();
    }

    public GroupFrame getCommonFrame() {
        return commonFrame;
    }

    @Override
    public boolean sameReference(Struct _other) {
        if (!(_other instanceof FrameStruct)) {
            return false;
        }
        return commonFrame == ((FrameStruct)_other).commonFrame;
    }

    public void dispose() {
        commonFrame.dispose();
    }
    public void addWindowEvent(WindowListener _event) {
        commonFrame.addWindowListener(_event);
    }
}
