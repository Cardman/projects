package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class SelectCallStackEvent implements AbsActionListener {
    private AbsDebuggerGui window;
    private int index;
    public SelectCallStackEvent(AbsDebuggerGui _d, int _i) {
        window = _d;
        index = _i;
    }

    @Override
    public void action() {
        window.updateGui(index);
    }
}
