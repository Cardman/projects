package aiki.gui.dialogs.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import aiki.gui.dialogs.SelectDialog;

public class ValidateSelectionEvent extends MouseAdapter {

    private SelectDialog dialog;

    public ValidateSelectionEvent(SelectDialog _dialog) {
        dialog = _dialog;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        dialog.validateChoice();
    }
}
