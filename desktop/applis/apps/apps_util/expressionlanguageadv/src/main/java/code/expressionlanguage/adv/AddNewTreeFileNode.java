package code.expressionlanguage.adv;

import code.gui.TextAnswerValue;
import code.gui.events.AbsActionListener;

public final class AddNewTreeFileNode implements AbsActionListener {
    private final WindowCdmEditor editor;

    public AddNewTreeFileNode(WindowCdmEditor _ed) {
        this.editor = _ed;
    }

    @Override
    public void action() {
        String lg_ = editor.getCommonFrame().getLanguageKey();
        TextAnswerValue confirmDialog_ = editor.getConfirmDialogText().input(editor.getCommonFrame(), "", "", "", lg_);
        editor.fileOrFolder(confirmDialog_);
    }
}
