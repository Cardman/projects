package cards.gui.dialogs.events;

import cards.gui.WindowCards;
import cards.gui.dialogs.DialogVaryingPlayerNumber;
import code.gui.events.AbsChangeListener;

public class ListenerPlayers implements AbsChangeListener {

    private DialogVaryingPlayerNumber dialog;
    private WindowCards window;

    public ListenerPlayers(DialogVaryingPlayerNumber _dialog, WindowCards _window) {
        dialog = _dialog;
        window = _window;
    }

    @Override
    public void stateChanged() {
        dialog.validateNbPlayers(window);
    }
}
