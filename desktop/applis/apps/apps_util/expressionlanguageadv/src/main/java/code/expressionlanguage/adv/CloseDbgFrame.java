package code.expressionlanguage.adv;

import code.gui.events.AbsWindowListenerClosing;
import code.threads.AbstractAtomicBoolean;

public final class CloseDbgFrame implements AbsWindowListenerClosing {
    private final AbsDebuggerGui gui;

    public CloseDbgFrame(AbsDebuggerGui _g) {
        gui = _g;
    }

    @Override
    public void windowClosing() {
        gui.getMenuManage().close();
        gui.closeAll();
        CancelFramePointsEvent.act(gui);
        AbstractAtomicBoolean s_ = gui.getStopDbg();
        s_.set(true);
    }
}
