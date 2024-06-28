package code.expressionlanguage.adv;

import code.expressionlanguage.ContextEl;
import code.gui.events.AbsActionListener;

public final class DbgStopRenderEvent implements AbsActionListener {
    private final ContextEl context;

    public DbgStopRenderEvent(ContextEl _c) {
        this.context = _c;
    }

    @Override
    public void action() {
        context.getInterrupt().set(true);
    }
}
