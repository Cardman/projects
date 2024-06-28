package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class CloseTreeDialog implements AbsActionListener {
    private final WindowWithTreeImpl editor;

    public CloseTreeDialog(WindowWithTreeImpl _ed) {
        editor = _ed;
    }
    @Override
    public void action() {
        editor.clearTreeDialog();
    }
}
