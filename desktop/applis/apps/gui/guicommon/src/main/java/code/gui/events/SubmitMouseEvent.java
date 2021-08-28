package code.gui.events;

import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.SingleFileSelection;

public class SubmitMouseEvent implements AbsActionListener {

    private SingleFileSelection dialog;

    public SubmitMouseEvent(SingleFileSelection _dialog) {
        dialog = _dialog;
    }

    @Override
    public void action() {
        dialog.submitIfVisible();
    }
}
