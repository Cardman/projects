package code.expressionlanguage.adv;

import code.gui.events.AbsWindowListenerClosing;

public final class CancelWpFormEvent implements AbsWindowListenerClosing {
    private final AbsDebuggerGui window;

    public CancelWpFormEvent(AbsDebuggerGui _w) {
        this.window = _w;
    }

    @Override
    public void windowClosing() {
        act(window);
    }

    static void act(AbsDebuggerGui _w) {
        _w.getFrameWpForm().getCommonFrame().setVisible(false);
        _w.getFrameWpForm().setSelectedWp(null);
    }
}
