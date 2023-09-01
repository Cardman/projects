package code.expressionlanguage.adv;

import code.expressionlanguage.exec.StepDbgActionEnum;
import code.expressionlanguage.options.ResultContext;
import code.gui.events.AbsActionListener;

public final class DbgNextBpEvent implements AbsActionListener {
    private final AbsDebuggerGui window;
    private final StepDbgActionEnum keep;
    private final ResultContext currentResult;

    public DbgNextBpEvent(AbsDebuggerGui _w, StepDbgActionEnum _k, ResultContext _res) {
        this.window = _w;
        keep = _k;
        currentResult = _res;
    }

    @Override
    public void action() {
        window.disableNext();
        window.getDetailAll().setVisible(false);
        window.currentThreadActions(new DbgLaunchTask(window, keep, currentResult));
    }
}
