package aiki.gui.dialogs.events;

import aiki.gui.dialogs.SelectHealingItem;
import code.gui.events.AbsWindowListenerClosing;

public final class ClosingSelectHealingItem implements AbsWindowListenerClosing {
    private final SelectHealingItem selectDialog;

    public ClosingSelectHealingItem(SelectHealingItem _s) {
        this.selectDialog = _s;
    }

    @Override
    public void windowClosing() {
        selectDialog.getMainWindow().getModal().set(false);
        selectDialog.closeWindow();
    }
}
