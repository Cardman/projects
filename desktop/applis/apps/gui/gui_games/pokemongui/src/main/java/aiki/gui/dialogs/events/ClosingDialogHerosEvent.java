package aiki.gui.dialogs.events;

import aiki.gui.WindowAiki;
import code.gui.events.AbsWindowListenerClosing;

public final class ClosingDialogHerosEvent implements AbsWindowListenerClosing {
    private final WindowAiki windowAiki;
    public ClosingDialogHerosEvent(WindowAiki _win) {
        windowAiki = _win;
    }

    @Override
    public void windowClosing() {
        windowAiki.getModal().set(false);
    }
}
