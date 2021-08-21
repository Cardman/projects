package code.gui.events;

import code.gui.AbsCloseableDialog;

public class CrossClosingDialogEvent extends AbsWindowListenerClosing {

    private AbsCloseableDialog dialog;

    public CrossClosingDialogEvent(AbsCloseableDialog _dialog) {
        dialog = _dialog;
    }

    @Override
    public void windowClosing() {
        dialog.closeWindow();
    }
}
