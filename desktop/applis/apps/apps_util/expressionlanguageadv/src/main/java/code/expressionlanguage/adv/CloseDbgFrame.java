package code.expressionlanguage.adv;

import code.gui.events.AbsWindowListenerClosing;

public final class CloseDbgFrame implements AbsWindowListenerClosing {
    private final AbsDebuggerGui gui;
    private final AbsOpenFrameInteract associated;

    public CloseDbgFrame(AbsDebuggerGui _g,AbsOpenFrameInteract _a) {
        gui = _g;
        associated = _a;
    }

    @Override
    public void windowClosing() {
        associated.close();
        CancelBpFormEvent.act(gui);
        gui.getStopDbg().set(true);
    }
}
