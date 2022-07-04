package aiki.gui.dialogs.events;

import aiki.gui.dialogs.ProgressingDialog;
import code.gui.AbsCloseableDialog;

public final class ClosingProgressingDialog implements AbsCloseableDialog {
    private final ProgressingDialog progressingDialog;

    public ClosingProgressingDialog(ProgressingDialog _pr) {
        this.progressingDialog = _pr;
    }

    @Override
    public void closeWindow() {
        progressingDialog.st();
    }
}
