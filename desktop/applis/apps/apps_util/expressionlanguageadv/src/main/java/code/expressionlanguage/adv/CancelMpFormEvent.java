package code.expressionlanguage.adv;

import code.gui.events.AbsWindowListenerClosing;

public final class CancelMpFormEvent implements AbsWindowListenerClosing {
    private final AbsDebuggerGui window;

    public CancelMpFormEvent(AbsDebuggerGui _w) {
        this.window = _w;
    }

    @Override
    public void windowClosing() {
        act(window);
    }

    static void act(AbsDebuggerGui _w) {
        _w.getFrameMpForm().getCommonFrame().setVisible(false);
        _w.getFrameMpForm().setSelectedMp(null);
    }
}
