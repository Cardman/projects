package aiki.gui.components.listeners;

import code.gui.AbsPanel;
import code.gui.Packable;
import code.gui.events.AbsActionListener;
import code.threads.AbstractAtomicBooleanCore;

public final class HidePkDetailContentEvent implements AbsActionListener {
    private final AbsPanel content;
    private final Packable packable;
    private final AbstractAtomicBooleanCore modal;

    public HidePkDetailContentEvent(AbsPanel _c, Packable _p, AbstractAtomicBooleanCore _at) {
        this.content = _c;
        this.packable = _p;
        this.modal = _at;
    }

    @Override
    public void action() {
        modal.set(false);
        content.removeAll();
        content.setVisible(false);
        packable.pack();
    }
}
