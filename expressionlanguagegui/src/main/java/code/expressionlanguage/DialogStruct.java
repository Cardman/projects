package code.expressionlanguage;

import code.gui.WithListener;

public final class DialogStruct extends WindowStruct {
    private OtherDialog dialog;
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
    public void pack() {
        dialog.pack();
    }

    @Override
    public String getClassName(ExecutableCode _contextEl) {
        return ((LgNamesGui) _contextEl.getContextEl().getStandards()).getAliasDialog();
    }
}
