package cards.gui.dialogs.events;

import cards.gui.WindowCardsInt;
import cards.gui.dialogs.DialogDisplaying;
import code.gui.events.AbsActionListener;

public class RemoveSuitEvent implements AbsActionListener {

    private DialogDisplaying dialog;
    private WindowCardsInt window;

    public RemoveSuitEvent(DialogDisplaying _dialog, WindowCardsInt _window) {
        dialog = _dialog;
        window = _window;
    }

    @Override
    public void action() {
        dialog.removeSuit(window);
    }
}
