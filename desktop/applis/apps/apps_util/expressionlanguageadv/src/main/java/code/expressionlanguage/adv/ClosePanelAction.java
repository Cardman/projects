package code.expressionlanguage.adv;

import code.gui.AbsPanel;
import code.gui.AbsTextPane;
import code.gui.events.AbsActionListener;

public final class ClosePanelAction implements AbsActionListener {
    private final AbsPanel pan;
    private final AbsTextPane editor;

    public ClosePanelAction(AbsPanel _p, AbsTextPane _e) {
        this.pan = _p;
        editor = _e;
    }

    @Override
    public void action() {
        pan.setVisible(false);
        editor.requestFocus();
    }
}
