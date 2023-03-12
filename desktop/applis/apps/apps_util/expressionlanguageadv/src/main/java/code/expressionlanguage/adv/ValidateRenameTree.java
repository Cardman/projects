package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class ValidateRenameTree implements AbsActionListener {
    private final WindowWithTreeImpl editor;

    public ValidateRenameTree(WindowWithTreeImpl _ed) {
        editor = _ed;
    }
    @Override
    public void action() {
        editor.renameValidate();
    }
}
