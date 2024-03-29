package code.expressionlanguage.adv;

import code.gui.AbsCommonFrame;
import code.gui.EnabledMenu;
import code.gui.events.AbsWindowListenerClosing;

public final class CloseFrame implements AbsWindowListenerClosing {
    private final AbsCommonFrame dialog;
    private final EnabledMenu associated;

    public CloseFrame(AbsCommonFrame _d, EnabledMenu _c) {
        dialog = _d;
        associated = _c;
    }

    @Override
    public void windowClosing() {
        dialog.setVisible(false);
        associated.setEnabled(true);
    }
}
