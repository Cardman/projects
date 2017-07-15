package cards.gui.dialogs.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cards.gui.dialogs.DialogDisplaying;

public class ValidateDisplayingEvent extends MouseAdapter {

    private DialogDisplaying dialog;

    public ValidateDisplayingEvent(DialogDisplaying _dialog) {
        dialog = _dialog;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        dialog.validateDisplaying();
    }
}
