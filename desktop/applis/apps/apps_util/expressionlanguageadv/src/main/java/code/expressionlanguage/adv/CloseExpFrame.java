package code.expressionlanguage.adv;

import code.gui.events.AbsWindowListenerClosing;

public final class CloseExpFrame implements AbsWindowListenerClosing {
    private final WindowExpressionEditor dialog;
    private final AbsOpenFrameInteract associated;

    public CloseExpFrame(WindowExpressionEditor _d, AbsOpenFrameInteract _a) {
        dialog = _d;
        associated = _a;
    }

    @Override
    public void windowClosing() {
        dialog.closeSecs();
        associated.close();
    }
}
