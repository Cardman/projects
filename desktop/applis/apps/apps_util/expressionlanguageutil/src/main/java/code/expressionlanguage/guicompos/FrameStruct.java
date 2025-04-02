package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;
import code.gui.AbsOtherFrame;

public final class FrameStruct extends WindowStruct {
//    private final AbsOtherFrame commonFrame;
    public FrameStruct(AbsOtherFrame _frame) {
        super(_frame);
//        commonFrame = _frame;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return ((LgNamesGui) _contextEl.getStandards()).getGuiAliases().getAliasFrame();
    }


}
