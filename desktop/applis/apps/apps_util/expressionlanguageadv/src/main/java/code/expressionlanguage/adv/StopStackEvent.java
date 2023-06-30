package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class StopStackEvent implements AbsActionListener {
    private final AbsDebuggerGui window;

    public StopStackEvent(AbsDebuggerGui _w) {
        this.window = _w;
    }
    @Override
    public void action() {
        window.getStopDbg().set(true);
    }
}
