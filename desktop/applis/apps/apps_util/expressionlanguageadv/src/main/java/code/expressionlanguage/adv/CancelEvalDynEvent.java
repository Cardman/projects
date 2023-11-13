package code.expressionlanguage.adv;

import code.gui.AbsButton;
import code.gui.events.AbsActionListener;
import code.threads.AbstractAtomicBoolean;

public class CancelEvalDynEvent implements AbsActionListener {
    private final AbsDebuggerGui win;
    private final AbsButton stopping;
    private final AbstractAtomicBoolean stopped;
    public CancelEvalDynEvent(AbsDebuggerGui _window, AbsButton _stop, AbstractAtomicBoolean _in) {
        win = _window;
        stopping = _stop;
        stopped = _in;
    }

    @Override
    public void action() {
        stopped.set(true);
        win.getButtons().removeObj(stopping);
//        win.getCancelDynWatch().remove(stopping);
        win.getCommonFrame().pack();
    }
}
