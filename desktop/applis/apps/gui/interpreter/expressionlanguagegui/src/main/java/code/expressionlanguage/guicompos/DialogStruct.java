package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.OtherDialog;
import code.gui.WithListener;

public final class DialogStruct extends WindowStruct {
    private OtherDialog dialog;
    private Struct menuBar = NullStruct.NULL_VALUE;
    public DialogStruct(OtherDialog _frame) {
        dialog = _frame;
    }

    @Override
    protected WithListener getAbstractWindow() {
        return getDialog();
    }

    public OtherDialog getDialog() {
        return dialog;
    }

    @Override
    public Struct getMenuBar() {
        return menuBar;
    }

    @Override
    public void setMenuBar(Struct _arg) {
        if (_arg instanceof MenuBarStruct) {
            menuBar = _arg;
            dialog.setJMenuBar(((MenuBarStruct)_arg).getMenuBar());
        }
    }

    @Override
    public void pack() {
        dialog.pack();
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return ((LgNamesGui) _contextEl.getStandards()).getGuiAliases().getAliasDialog();
    }
}
