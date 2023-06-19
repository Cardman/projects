package code.expressionlanguage.adv;

import code.expressionlanguage.exec.StepDbgActionEnum;
import code.gui.events.AbsActionListener;

public final class DbgNextBpEvent implements AbsActionListener {
    private final AbsDebuggerGui window;
    private final StepDbgActionEnum keep;

    public DbgNextBpEvent(AbsDebuggerGui _w, StepDbgActionEnum _k) {
        this.window = _w;
        keep = _k;
    }

    @Override
    public void action() {
        window.getNextAction().setEnabled(false);
        window.getNextInstruction().setEnabled(false);
        window.getNextGoUp().setEnabled(false);
        window.getNextInMethod().setEnabled(false);
        window.getDetailAll().setVisible(false);
        window.getDebugActions().submit(new DbgLaunchTask(window, keep));
    }
}
