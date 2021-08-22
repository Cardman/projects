package aiki.gui.dialogs.events;

import aiki.gui.dialogs.DialogSoftParams;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerRel;

public class ValidateSoftParams extends AbsMouseListenerRel {

    private final DialogSoftParams dialog;

    public ValidateSoftParams(DialogSoftParams _dialog) {
        dialog = _dialog;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        dialog.validateChoices();
    }
}
