package code.expressionlanguage.adv;

import code.expressionlanguage.exec.StepDbgActionEnum;
import code.expressionlanguage.options.ResultContext;

public final class DbgLaunchTask implements Runnable {
    private final AbsDebuggerGui window;
    private final StepDbgActionEnum step;
    private final ResultContext currentResult;

    public DbgLaunchTask(AbsDebuggerGui _w, StepDbgActionEnum _s) {
        this.window = _w;
        this.step = _s;
        ResultContext curr_ = _w.getCurrentResult();
        if (_s == StepDbgActionEnum.CURSOR) {
            window.possibleSelect(_w.getTabbedPane().getSelectedIndex(), curr_);
        }
        this.currentResult = curr_;
    }

    @Override
    public void run() {
        window.next(step, currentResult);
    }
}
