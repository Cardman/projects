package code.expressionlanguage.adv;

import code.expressionlanguage.options.ResultContext;
import code.gui.events.AbsActionListener;

public final class DbgLaunchEvent implements AbsActionListener {
    private final AbsDebuggerGui window;
    private final ResultContext currentResult;

    public DbgLaunchEvent(AbsDebuggerGui _w, ResultContext _curr) {
        this.window = _w;
        currentResult = _curr;
    }

    @Override
    public void action() {
        window.launchDebug(currentResult);
    }
}
