package code.gui.events;

import code.gui.*;

public class ClosingDialogEvent extends AbsMouseListenerRel {

    private AbsCloseableDialog dialog;

    public ClosingDialogEvent(AbsCloseableDialog _dialog) {
        dialog = _dialog;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        dialog.closeWindow();
    }
}
