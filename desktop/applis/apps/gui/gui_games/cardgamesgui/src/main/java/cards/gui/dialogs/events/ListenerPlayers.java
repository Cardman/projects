package cards.gui.dialogs.events;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import cards.gui.WindowCards;
import cards.gui.dialogs.DialogVaryingPlayerNumber;

public class ListenerPlayers implements ChangeListener {

    private DialogVaryingPlayerNumber dialog;
    private WindowCards window;

    public ListenerPlayers(DialogVaryingPlayerNumber _dialog, WindowCards _window) {
        dialog = _dialog;
        window = _window;
    }

    @Override
    public void stateChanged(ChangeEvent _e) {
        dialog.validateNbPlayers(window);
    }
}
