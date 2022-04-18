package aiki.gui.dialogs.events;

import aiki.gui.dialogs.DialogSoftParams;
import code.gui.events.AbsActionListener;

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
