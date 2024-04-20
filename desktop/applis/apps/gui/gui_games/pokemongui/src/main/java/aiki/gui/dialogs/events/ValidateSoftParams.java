package aiki.gui.dialogs.events;

import aiki.gui.WindowAiki;
import aiki.gui.dialogs.DialogSoftParams;
import code.gui.events.AbsActionListener;

public class ValidateSoftParams implements AbsActionListener {

    private final DialogSoftParams dialog;
    private final WindowAiki frame;

    public ValidateSoftParams(DialogSoftParams _dialog, WindowAiki _window) {
        dialog = _dialog;
        frame = _window;
    }

    @Override
    public void action() {
        dialog.validateChoices(frame);
    }
}
