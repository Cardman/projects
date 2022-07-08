package code.gui.events;

import code.gui.AbsCloseableDialog;
import code.gui.AbsDialog;

public abstract class AbstractClosingDialogListEvent {

    private final AbsDialog cur;
    private final AbsCloseableDialog dialog;

    protected AbstractClosingDialogListEvent(AbsDialog _current, AbsCloseableDialog _dia) {
        cur = _current;
        dialog = _dia;
    }
    public void close() {
        cur.closeWindow();
        if (dialog != null) {
            dialog.closeWindow();
        }
    }
}
