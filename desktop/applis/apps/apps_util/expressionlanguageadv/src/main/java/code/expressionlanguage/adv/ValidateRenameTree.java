package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class ValidateRenameTree implements AbsActionListener {
    private final WindowCdmEditor editor;

    public ValidateRenameTree(WindowCdmEditor _ed) {
        editor = _ed;
    }
    @Override
    public void action() {
        editor.renameValidate();
    }
}
