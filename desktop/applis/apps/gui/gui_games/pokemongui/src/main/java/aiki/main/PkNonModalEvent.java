package aiki.main;

import code.gui.events.AbsActionListenerAct;
import code.threads.AbstractAtomicBooleanCore;

public final class PkNonModalEvent implements AbsActionListenerAct {
    private final AbstractAtomicBooleanCore modal;

    public PkNonModalEvent(AbstractAtomicBooleanCore _m) {
        this.modal = _m;
    }

    @Override
    public boolean act() {
        return !modal.get();
    }
}
