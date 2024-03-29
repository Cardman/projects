package cards.gui.dialogs.events;

import cards.gui.WindowCardsInt;
import cards.gui.dialogs.DialogVaryingPlayerNumber;
import code.gui.events.AbsChangeListener;

public class ListenerPlayers implements AbsChangeListener {

    private DialogVaryingPlayerNumber dialog;
    private WindowCardsInt window;

    public ListenerPlayers(DialogVaryingPlayerNumber _dialog, WindowCardsInt _window) {
        dialog = _dialog;
        window = _window;
    }

    @Override
    public void stateChanged() {
        dialog.validateNbPlayers(window);
    }
}
