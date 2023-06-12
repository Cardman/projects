package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class DbgLaunchEvent implements AbsActionListener {
    private final AbsDebuggerGui window;

    public DbgLaunchEvent(AbsDebuggerGui _w) {
        this.window = _w;
    }

    @Override
    public void action() {
        window.launchDebug();
    }
}
