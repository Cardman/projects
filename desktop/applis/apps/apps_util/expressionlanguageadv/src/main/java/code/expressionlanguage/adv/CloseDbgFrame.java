package code.expressionlanguage.adv;

import code.gui.events.AbsWindowListenerClosing;

public final class CloseDbgFrame implements AbsWindowListenerClosing {
    private final AbsDebuggerGui gui;

    public CloseDbgFrame(AbsDebuggerGui _g) {
        gui = _g;
    }

    @Override
    public void windowClosing() {
        gui.getMenuManage().close();
        gui.closeAll();
        CancelBpFormEvent.act(gui);
        CancelMpFormEvent.act(gui);
        CancelWpFormEvent.act(gui);
        CancelFramePointsEvent.act(gui);
        gui.getStopDbg().set(true);
    }
}
