package aiki.gui.dialogs.events;

import aiki.gui.dialogs.SelectDialog;
import code.gui.events.AbsWindowListenerClosing;

public final class ClosingSelectItem implements AbsWindowListenerClosing {
    private final SelectDialog selectDialog;

    public ClosingSelectItem(SelectDialog _s) {
        this.selectDialog = _s;
    }

    @Override
    public void windowClosing() {
        selectDialog.getMainWindow().getModal().set(false);
        selectDialog.getFacade().clearFiltersItem();
    }
}
