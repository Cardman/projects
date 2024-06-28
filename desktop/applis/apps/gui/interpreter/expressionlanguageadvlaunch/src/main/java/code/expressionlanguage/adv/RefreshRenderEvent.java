package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public class RefreshRenderEvent implements AbsActionListener {
    private final AbsDebuggerGui window;
    public RefreshRenderEvent(AbsDebuggerGui _w) {
        window = _w;
    }

    @Override
    public void action() {
        window.refreshRender();
    }
}
