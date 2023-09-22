package code.expressionlanguage.adv;

import code.expressionlanguage.exec.StepDbgActionEnum;
import code.expressionlanguage.options.ResultContext;

public final class DbgLaunchTask implements Runnable {
    private final AbsDebuggerGui window;
    private final StepDbgActionEnum step;
    private final ResultContext currentResult;

    public DbgLaunchTask(AbsDebuggerGui _w, StepDbgActionEnum _s, ResultContext _curr) {
        this.window = _w;
        this.step = _s;
        if (_s == StepDbgActionEnum.CURSOR_INSTRUCTION) {
            window.possibleSelectInstruction(_w.getTabbedPane().getSelectedIndex(), _curr);
        }
        if (_s == StepDbgActionEnum.CURSOR_EXPRESSION) {
            window.possibleSelectExpression(_w.getTabbedPane().getSelectedIndex(), _curr);
        }
        this.currentResult = _curr;
    }

    @Override
    public void run() {
        window.next(step, currentResult);
    }
}
