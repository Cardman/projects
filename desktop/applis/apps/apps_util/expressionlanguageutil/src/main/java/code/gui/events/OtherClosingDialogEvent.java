package code.gui.events;

import code.gui.OtherConfirmDialog;

public class OtherClosingDialogEvent implements AbsActionListener {

    private final OtherConfirmDialog dialog;

    public OtherClosingDialogEvent(OtherConfirmDialog _dialog) {
        dialog = _dialog;
    }

    @Override
    public void action() {
        dialog.closeWindow();
    }
}
