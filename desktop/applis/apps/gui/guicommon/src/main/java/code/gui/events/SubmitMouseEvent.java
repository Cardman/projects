package code.gui.events;

import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.SingleFileSelection;

public class SubmitMouseEvent extends AbsMouseListenerRel {

    private SingleFileSelection dialog;

    public SubmitMouseEvent(SingleFileSelection _dialog) {
        dialog = _dialog;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        dialog.submitIfVisible();
    }
}
