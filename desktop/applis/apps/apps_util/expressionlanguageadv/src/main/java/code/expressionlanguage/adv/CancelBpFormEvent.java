package code.expressionlanguage.adv;

import code.gui.events.AbsWindowListenerClosing;

public final class CancelBpFormEvent implements AbsWindowListenerClosing {
    private final AbsDebuggerGui window;

    public CancelBpFormEvent(AbsDebuggerGui _w) {
        this.window = _w;
    }

    @Override
    public void windowClosing() {
        act(window);
    }

    static void act(AbsDebuggerGui _w) {
        _w.getFrameBpForm().getCommonFrame().setVisible(false);
        _w.getFrameBpForm().setSelectedPb(null);
    }
}
