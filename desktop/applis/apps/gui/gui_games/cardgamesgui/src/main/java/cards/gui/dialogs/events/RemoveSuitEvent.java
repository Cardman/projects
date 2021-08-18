package cards.gui.dialogs.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cards.gui.WindowCards;
import cards.gui.dialogs.DialogDisplaying;

public class RemoveSuitEvent extends MouseAdapter {

    private DialogDisplaying dialog;
    private WindowCards window;

    public RemoveSuitEvent(DialogDisplaying _dialog, WindowCards _window) {
        dialog = _dialog;
        window = _window;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        dialog.removeSuit(window);
    }
}
