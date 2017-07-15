package cards.gui.dialogs.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cards.gui.dialogs.DialogDisplaying;

public class RemoveSuitEvent extends MouseAdapter {

    private DialogDisplaying dialog;

    public RemoveSuitEvent(DialogDisplaying _dialog) {
        dialog = _dialog;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        dialog.removeSuit();
    }
}
