package code.expressionlanguage.adv;

import code.gui.AbstractMutableTreeNode;
import code.gui.TextAnswerValue;
import code.gui.events.AbsActionListener;

public final class RenameTreeAction implements AbsActionListener {
    private final WindowCdmEditor editor;

    public RenameTreeAction(WindowCdmEditor _ed) {
        editor = _ed;
    }
    @Override
    public void action() {
        AbstractMutableTreeNode sel_ = editor.getFolderSystem().selectEvt();
        if (sel_ == null) {
            return;
        }
        String str_ = WindowCdmEditor.buildPath(sel_);
        String lg_ = editor.getCommonFrame().getLanguageKey();
        TextAnswerValue confirmDialog_ = editor.getConfirmDialogText().input(editor.getCommonFrame(), "", str_, "", lg_);
        editor.rename(confirmDialog_,sel_,str_);
    }
}
