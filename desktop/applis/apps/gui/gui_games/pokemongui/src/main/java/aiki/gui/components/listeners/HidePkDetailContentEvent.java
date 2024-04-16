package aiki.gui.components.listeners;

import code.gui.AbsPanel;
import code.gui.events.AbsActionListener;

public final class HidePkDetailContentEvent implements AbsActionListener {
    private final AbsPanel content;

    public HidePkDetailContentEvent(AbsPanel _c) {
        this.content = _c;
    }

    @Override
    public void action() {
        content.removeAll();
        content.setVisible(false);
    }
}
