package cards.gui.dialogs.events;

import cards.gui.dialogs.DialogTarot;
import code.gui.events.AbsActionListener;

public class ListenerHandful implements AbsActionListener {

    private DialogTarot dialog;

    public ListenerHandful(DialogTarot _dialog) {
        dialog = _dialog;
    }

    @Override
    public void action() {
        dialog.validateHandfulTrumps();
    }
}
