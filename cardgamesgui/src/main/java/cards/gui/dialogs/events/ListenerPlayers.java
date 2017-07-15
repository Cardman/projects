package cards.gui.dialogs.events;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import cards.gui.dialogs.DialogVaryingPlayerNumber;

public class ListenerPlayers implements ChangeListener {

    private DialogVaryingPlayerNumber dialog;

    public ListenerPlayers(DialogVaryingPlayerNumber _dialog) {
        dialog = _dialog;
    }

    @Override
    public void stateChanged(ChangeEvent _e) {
        dialog.validateNbPlayers();
    }
}
