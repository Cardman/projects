package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;
import code.gui.AbsOtherDialog;

public final class DialogStruct extends WindowStruct {
    public DialogStruct(AbsOtherDialog _frame) {
        super(_frame);
    }


    @Override
    public String getClassName(ContextEl _contextEl) {
        return ((LgNamesGui) _contextEl.getStandards()).getGuiAliases().getAliasDialog();
    }
}
