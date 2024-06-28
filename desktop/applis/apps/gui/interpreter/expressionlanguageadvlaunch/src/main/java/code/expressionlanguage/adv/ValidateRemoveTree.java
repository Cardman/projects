package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class ValidateRemoveTree implements AbsActionListener {
    private final WindowWithTreeImpl editor;

    public ValidateRemoveTree(WindowWithTreeImpl _ed) {
        editor = _ed;
    }
    @Override
    public void action() {
        editor.removeValidate();
    }
}
