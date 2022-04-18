package aiki.gui.dialogs.events;

import aiki.gui.dialogs.SelectDialog;
import code.gui.events.AbsActionListener;

public class ValidateSelectionEvent implements AbsActionListener {

    private SelectDialog dialog;

    public ValidateSelectionEvent(SelectDialog _dialog) {
        dialog = _dialog;
    }

    @Override
    public void action() {
        dialog.validateChoice();
    }
}
