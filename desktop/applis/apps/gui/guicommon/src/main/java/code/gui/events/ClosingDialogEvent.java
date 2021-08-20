package code.gui.events;

import code.gui.AbsMouseButtons;
import code.gui.AbsMouseKeyState;
import code.gui.AbsMouseLocation;
import code.gui.Dialog;

public class ClosingDialogEvent extends AbsMouseListenerRel {

    private Dialog dialog;

    public ClosingDialogEvent(Dialog _dialog) {
        dialog = _dialog;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons) {
        dialog.closeWindow();
    }
}
