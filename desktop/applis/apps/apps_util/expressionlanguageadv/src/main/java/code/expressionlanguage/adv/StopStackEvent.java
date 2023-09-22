package code.expressionlanguage.adv;

import code.expressionlanguage.options.ResultContext;
import code.gui.events.AbsActionListener;

public final class StopStackEvent implements AbsActionListener {
    private final AbsDebuggerGui window;
    private final ResultContext currentResult;

    public StopStackEvent(AbsDebuggerGui _w, ResultContext _cur) {
        this.window = _w;
        currentResult = _cur;
    }
    @Override
    public void action() {
        currentResult.getContext().getInterrupt().set(true);
        window.getStopStack().setEnabled(false);
        window.getStoppedClick().set(true);
        window.getPauseStack().setEnabled(false);
        window.getAnalyzeMenu().setEnabled(true);
        window.getSelectEnter().setEnabled(true);
        window.disableNext();
        window.getDetailAll().setVisible(false);
        window.getCallButtons().clear();
        window.getCallButtonsRender().clear();
        window.getCallStack().removeAll();
        window.getCallStackRender().removeAll();
        window.getCommonFrame().pack();
    }
}
