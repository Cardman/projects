package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.AbsOtherDialog;

public final class DialogStruct extends WindowStruct {
    private Struct menuBar = NullStruct.NULL_VALUE;
    public DialogStruct(AbsOtherDialog _frame) {
        super(_frame);
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

    @Override
    public String getClassName(ContextEl _contextEl) {
        return ((LgNamesGui) _contextEl.getStandards()).getGuiAliases().getAliasDialog();
    }
}
