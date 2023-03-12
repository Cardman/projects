package code.expressionlanguage.adv;

import code.gui.AbsMenuItem;
import code.gui.events.AbsWindowListenerClosing;

public final class CloseExpFrame implements AbsWindowListenerClosing {
    private final WindowExpressionEditor dialog;
    private final AbsMenuItem associated;

    public CloseExpFrame(WindowExpressionEditor _d, AbsMenuItem _c) {
        dialog = _d;
        associated = _c;
    }

    @Override
    public void windowClosing() {
        dialog.closeSecs();
        associated.setEnabled(true);
    }
}
