package code.expressionlanguage.adv;

import code.expressionlanguage.options.ResultContext;
import code.gui.events.AbsActionListener;

public final class PauseStackEvent implements AbsActionListener {
    private final AbsDebuggerGui window;
    private final ResultContext currentResult;

    public PauseStackEvent(AbsDebuggerGui _w, ResultContext _res) {
        this.window = _w;
        currentResult = _res;
    }
    @Override
    public void action() {
        currentResult.getContext().pausedLoop().set(true);
        window.getPauseStack().setEnabled(false);
    }
}
