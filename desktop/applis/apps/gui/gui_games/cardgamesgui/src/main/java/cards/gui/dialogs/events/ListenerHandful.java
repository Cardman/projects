package cards.gui.dialogs.events;

import cards.gui.dialogs.DialogVaryingHandful;
import code.gui.events.AbsActionListener;

public class ListenerHandful implements AbsActionListener {

    private final DialogVaryingHandful dialog;

    public ListenerHandful(DialogVaryingHandful _dialog) {
        dialog = _dialog;
    }

    @Override
    public void action() {
        dialog.validateHandfulTrumps();
    }
}
