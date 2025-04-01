package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.AbsOtherFrame;

public final class FrameStruct extends WindowStruct {
//    private final AbsOtherFrame commonFrame;
    private Struct menuBar = NullStruct.NULL_VALUE;
    public FrameStruct(AbsOtherFrame _frame) {
        super(_frame);
//        commonFrame = _frame;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return ((LgNamesGui) _contextEl.getStandards()).getGuiAliases().getAliasFrame();
    }

    @Override
    public Struct getMenuBar() {
        return menuBar;
    }

    @Override
    public void setMenuBar(Struct _arg) {
        if (_arg instanceof MenuBarStruct) {
            menuBar = _arg;
            getAbstractWindow().setJMenuBar(((MenuBarStruct)_arg).getMenuBar());
        }
    }

}
