package aiki.gui.dialogs.events;

import aiki.gui.dialogs.SelectHealingItem;
import code.gui.AbsCloseableDialog;

public final class ClosingSelectHealingItem implements AbsCloseableDialog {
    private final SelectHealingItem selectDialog;

    public ClosingSelectHealingItem(SelectHealingItem _s) {
        this.selectDialog = _s;
    }

    @Override
    public void closeWindow() {
        selectDialog.closeWindow();
    }
}
