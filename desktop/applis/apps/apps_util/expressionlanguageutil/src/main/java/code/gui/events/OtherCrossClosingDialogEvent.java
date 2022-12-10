package code.gui.events;

import code.gui.OtherConfirmDialog;

public class OtherCrossClosingDialogEvent implements AbsWindowListenerClosing {

    private final OtherConfirmDialog dialog;

    public OtherCrossClosingDialogEvent(OtherConfirmDialog _dialog) {
        dialog = _dialog;
    }

    @Override
    public void windowClosing() {
        dialog.closeWindow();
    }
}
