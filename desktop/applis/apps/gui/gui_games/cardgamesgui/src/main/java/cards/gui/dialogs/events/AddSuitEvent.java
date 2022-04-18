package cards.gui.dialogs.events;

import cards.gui.dialogs.DialogDisplaying;
import code.gui.events.AbsActionListener;

public class AddSuitEvent implements AbsActionListener {

    private DialogDisplaying dialog;

    public AddSuitEvent(DialogDisplaying _dialog) {
        dialog = _dialog;
    }

    @Override
    public void action() {
        dialog.addSuit();
    }
}
