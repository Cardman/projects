package aiki.gui.dialogs.events;

import aiki.gui.dialogs.SelectDialog;
import code.gui.AbsCloseableDialog;

public final class ClosingSelectTm implements AbsCloseableDialog {
    private final SelectDialog selectDialog;

    public ClosingSelectTm(SelectDialog _s) {
        this.selectDialog = _s;
    }

    @Override
    public void closeWindow() {
        selectDialog.getFacade().clearFiltersMove();
    }
}
