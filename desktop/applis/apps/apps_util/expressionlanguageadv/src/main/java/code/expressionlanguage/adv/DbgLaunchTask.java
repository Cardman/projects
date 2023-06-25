package code.expressionlanguage.adv;

import code.expressionlanguage.exec.StepDbgActionEnum;

public final class DbgLaunchTask implements Runnable {
    private final AbsDebuggerGui window;
    private final StepDbgActionEnum step;

    public DbgLaunchTask(AbsDebuggerGui _w, StepDbgActionEnum _s) {
        this.window = _w;
        this.step = _s;
        if (_s == StepDbgActionEnum.CURSOR) {
            window.possibleSelect(_w.getTabbedPane().getSelectedIndex());
        }
    }

    @Override
    public void run() {
        window.next(step);
    }
}
