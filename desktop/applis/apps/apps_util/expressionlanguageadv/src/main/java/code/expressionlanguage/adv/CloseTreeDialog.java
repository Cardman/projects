package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class CloseTreeDialog implements AbsActionListener {
    private final WindowCdmEditor editor;

    public CloseTreeDialog(WindowCdmEditor _ed) {
        editor = _ed;
    }
    @Override
    public void action() {
        editor.clearTreeDialog();
    }
}
