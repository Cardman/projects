package aiki.gui.components.listeners;

import code.gui.AbsPanel;
import code.gui.Packable;
import code.gui.events.AbsActionListener;

public final class HidePkDetailContentEvent implements AbsActionListener {
    private final AbsPanel content;
    private final Packable packable;

    public HidePkDetailContentEvent(AbsPanel _c, Packable _p) {
        this.content = _c;
        this.packable = _p;
    }

    @Override
    public void action() {
        content.removeAll();
        content.setVisible(false);
        packable.pack();
    }
}
