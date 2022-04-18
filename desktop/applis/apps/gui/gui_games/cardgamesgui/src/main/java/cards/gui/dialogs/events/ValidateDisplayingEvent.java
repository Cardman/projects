package cards.gui.dialogs.events;

import cards.gui.dialogs.DialogDisplaying;
import code.gui.events.AbsActionListener;

public class ValidateDisplayingEvent implements AbsActionListener {

    private DialogDisplaying dialog;

    public ValidateDisplayingEvent(DialogDisplaying _dialog) {
        dialog = _dialog;
    }

    @Override
    public void action() {
        dialog.validateDisplaying();
    }
}
