package cards.gui.dialogs.events;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import cards.gui.MainWindow;
import cards.gui.dialogs.DialogVaryingPlayerNumber;

public class ListenerPlayers implements ChangeListener {

    private DialogVaryingPlayerNumber dialog;
    private MainWindow window;

    public ListenerPlayers(DialogVaryingPlayerNumber _dialog, MainWindow _window) {
        dialog = _dialog;
        window = _window;
    }

    @Override
    public void stateChanged(ChangeEvent _e) {
        dialog.validateNbPlayers(window);
    }
}
