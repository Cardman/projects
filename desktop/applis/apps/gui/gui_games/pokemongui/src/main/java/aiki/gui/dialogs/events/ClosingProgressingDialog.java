package aiki.gui.dialogs.events;

import aiki.gui.dialogs.ProgressingDialog;
import code.gui.events.AbsWindowListenerClosing;
import code.threads.AbstractAtomicBooleanCore;

public final class ClosingProgressingDialog implements AbsWindowListenerClosing {
    private final ProgressingDialog progressingDialog;
    private final AbstractAtomicBooleanCore modal;

    public ClosingProgressingDialog(ProgressingDialog _pr, AbstractAtomicBooleanCore _m) {
        this.progressingDialog = _pr;
        modal = _m;
    }

    @Override
    public void windowClosing() {
        modal.set(false);
        progressingDialog.st();
    }
}
