package aiki.gui.dialogs.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import aiki.gui.dialogs.DialogSoftParams;

public class ValidateSoftParams extends MouseAdapter {

    private final DialogSoftParams dialog;

    public ValidateSoftParams(DialogSoftParams _dialog) {
        dialog = _dialog;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        dialog.validateChoices();
    }
}
