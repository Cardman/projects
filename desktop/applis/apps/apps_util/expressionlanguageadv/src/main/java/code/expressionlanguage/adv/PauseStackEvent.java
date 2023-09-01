package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class PauseStackEvent implements AbsActionListener {
    private final AbsDebuggerGui window;

    public PauseStackEvent(AbsDebuggerGui _w) {
        this.window = _w;
    }
    @Override
    public void action() {
        window.getCurrentResult().getContext().pausedLoop().set(true);
        window.getPauseStack().setEnabled(false);
    }
}
