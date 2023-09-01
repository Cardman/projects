package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class StopStackEvent implements AbsActionListener {
    private final AbsDebuggerGui window;

    public StopStackEvent(AbsDebuggerGui _w) {
        this.window = _w;
    }
    @Override
    public void action() {
        window.stopDbg().set(true);
        window.getStopStack().setEnabled(false);
        window.getStoppedClick().set(true);
        window.getPauseStack().setEnabled(false);
        window.getAnalyzeMenu().setEnabled(true);
        window.getSelectEnter().setEnabled(true);
        window.disableNext();
        window.getDetailAll().setVisible(false);
        window.getCallButtons().clear();
        window.getCallStack().removeAll();
        window.getCommonFrame().pack();
    }
}
