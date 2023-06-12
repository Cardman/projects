package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class DbgNextBpEvent implements AbsActionListener {
    private final AbsDebuggerGui window;

    public DbgNextBpEvent(AbsDebuggerGui _w) {
        this.window = _w;
    }

    @Override
    public void action() {
        window.getNextAction().setEnabled(false);
        window.getDetailAll().setVisible(false);
        window.getDebugActions().submit(new DbgLaunchTask(window));
    }
}
