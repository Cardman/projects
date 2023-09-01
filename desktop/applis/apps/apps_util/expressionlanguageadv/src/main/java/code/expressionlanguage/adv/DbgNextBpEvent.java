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
        window.disableNext();
        window.getDetailAll().setVisible(false);
        window.currentThreadActions(new DbgLaunchTask(window, keep));
    }
}
