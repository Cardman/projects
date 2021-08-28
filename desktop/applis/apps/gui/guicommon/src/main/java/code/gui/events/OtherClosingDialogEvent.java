package code.gui.events;

import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.OtherConfirmDialog;

public class OtherClosingDialogEvent implements AbsActionListener {

    private OtherConfirmDialog dialog;

    public OtherClosingDialogEvent(OtherConfirmDialog _dialog) {
        dialog = _dialog;
    }

    @Override
    public void action() {
        dialog.closeWindow();
    }
}
