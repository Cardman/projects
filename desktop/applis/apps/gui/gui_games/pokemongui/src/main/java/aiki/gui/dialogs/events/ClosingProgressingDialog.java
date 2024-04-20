package aiki.gui.dialogs.events;

import aiki.gui.WindowAiki;
import aiki.gui.dialogs.ProgressingDialog;
import code.gui.events.AbsWindowListenerClosing;

public final class ClosingProgressingDialog implements AbsWindowListenerClosing {
    private final ProgressingDialog progressingDialog;
    private final WindowAiki frame;

    public ClosingProgressingDialog(ProgressingDialog _pr, WindowAiki _win) {
        this.progressingDialog = _pr;
        frame = _win;
    }

    @Override
    public void windowClosing() {
        frame.getModal().set(false);
        progressingDialog.st();
    }
}
