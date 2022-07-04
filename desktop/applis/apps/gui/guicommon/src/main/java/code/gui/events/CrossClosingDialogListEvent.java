package code.gui.events;

import code.gui.AbsCloseableDialog;
import code.gui.AbsDialog;

public class CrossClosingDialogListEvent implements AbsWindowListenerClosing {

    private final AbsDialog cur;
    private final AbsCloseableDialog dialog;

    public CrossClosingDialogListEvent(AbsDialog _current, AbsCloseableDialog _dia) {
        cur = _current;
        dialog = _dia;
    }

    @Override
    public void windowClosing() {
        cur.closeWindow();
        dialog.closeWindow();
    }
}
