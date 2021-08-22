package code.gui.events;

import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.OtherConfirmDialog;

public class OtherClosingDialogEvent extends AbsMouseListenerRel {

    private OtherConfirmDialog dialog;

    public OtherClosingDialogEvent(OtherConfirmDialog _dialog) {
        dialog = _dialog;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        dialog.closeWindow();
    }
}
