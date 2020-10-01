package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.OtherFrame;
import code.gui.WithListener;

public final class FrameStruct extends WindowStruct {
    private OtherFrame commonFrame;
    private Struct menuBar = NullStruct.NULL_VALUE;
    public FrameStruct(OtherFrame _frame) {
        commonFrame = _frame;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return ((LgNamesGui) _contextEl.getStandards()).getGuiAliases().getAliasFrame();
    }

    @Override
    protected WithListener getAbstractWindow() {
        return getCommonFrame();
    }

    @Override
    public void pack() {
        getCommonFrame().pack();
    }

    @Override
    public Struct getMenuBar() {
        return menuBar;
    }

    @Override
    public void setMenuBar(Struct _arg) {
        if (_arg instanceof MenuBarStruct) {
            menuBar = _arg;
            commonFrame.setJMenuBar(((MenuBarStruct)_arg).getMenuBar());
        }
    }

    public OtherFrame getCommonFrame() {
        return commonFrame;
    }

}
