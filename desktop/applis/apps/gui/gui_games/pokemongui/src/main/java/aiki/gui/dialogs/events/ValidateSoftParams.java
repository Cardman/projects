package aiki.gui.dialogs.events;

import aiki.gui.dialogs.DialogSoftParams;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsMouseListenerRel;

public class ValidateSoftParams implements AbsActionListener {

    private final DialogSoftParams dialog;

    public ValidateSoftParams(DialogSoftParams _dialog) {
        dialog = _dialog;
    }

    @Override
    public void action() {
        dialog.validateChoices();
    }
}
