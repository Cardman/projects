package code.gui.events;

import code.gui.AbsCloseableDialog;

public class CrossClosingDialogEvent implements AbsWindowListenerClosing {

    private final AbsCloseableDialog dialog;

    public CrossClosingDialogEvent(AbsCloseableDialog _dialog) {
        dialog = _dialog;
    }

    @Override
    public void windowClosing() {
        dialog.closeWindow();
    }
}
