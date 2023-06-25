package code.expressionlanguage.adv;

import code.gui.events.AbsWindowListenerClosing;

public final class CancelBpFormEvent implements AbsWindowListenerClosing {
    private final AbsDebuggerGui window;

    public CancelBpFormEvent(AbsDebuggerGui _w) {
        this.window = _w;
    }

    @Override
    public void windowClosing() {
        window.getFrameBpForm().getCommonFrame().setVisible(false);
        window.getFrameBpForm().setSelectedPb(null);
    }
}
