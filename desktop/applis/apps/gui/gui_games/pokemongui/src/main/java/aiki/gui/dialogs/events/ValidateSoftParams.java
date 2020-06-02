package aiki.gui.dialogs.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import aiki.gui.dialogs.SoftParams;

public class ValidateSoftParams extends MouseAdapter {

    private SoftParams dialog;

    public ValidateSoftParams(SoftParams _dialog) {
        dialog = _dialog;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        dialog.validateChoices();
    }
}
