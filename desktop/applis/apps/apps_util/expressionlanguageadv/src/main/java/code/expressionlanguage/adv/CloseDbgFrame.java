package code.expressionlanguage.adv;

import code.gui.events.AbsWindowListenerClosing;

public final class CloseDbgFrame implements AbsWindowListenerClosing {
    private final AbsOpenFrameInteract associated;

    public CloseDbgFrame(AbsOpenFrameInteract _a) {
        associated = _a;
    }

    @Override
    public void windowClosing() {
        associated.close();
    }
}
