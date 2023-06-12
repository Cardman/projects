package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class CancelBpFormEvent implements AbsActionListener {
    private final AbsDebuggerGui window;

    public CancelBpFormEvent(AbsDebuggerGui _w) {
        this.window = _w;
    }

    @Override
    public void action() {
        window.getBpForm().setVisible(false);
        window.setSelectedPb(null);
    }
}
