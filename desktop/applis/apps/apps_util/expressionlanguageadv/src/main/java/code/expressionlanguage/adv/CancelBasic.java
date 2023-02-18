package code.expressionlanguage.adv;

import code.gui.AbsDialog;
import code.gui.events.AbsActionListener;

public final class CancelBasic implements AbsActionListener {
    private final AbsDialog dialog;

    public CancelBasic(AbsDialog _d) {
        dialog = _d;
    }

    @Override
    public void action() {
        dialog.setVisible(false);
    }
}
