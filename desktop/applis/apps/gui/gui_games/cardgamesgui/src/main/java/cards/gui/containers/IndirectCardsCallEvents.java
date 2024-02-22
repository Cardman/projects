package cards.gui.containers;

import code.gui.initialize.AbsCompoFactory;

public final class IndirectCardsCallEvents implements IntCardsCallEvents {

    private final AbsCompoFactory compoFactory;

    public IndirectCardsCallEvents(AbsCompoFactory _c) {
        this.compoFactory = _c;
    }

    @Override
    public void call(Runnable _call) {
        compoFactory.invokeNow(_call);
    }
}
