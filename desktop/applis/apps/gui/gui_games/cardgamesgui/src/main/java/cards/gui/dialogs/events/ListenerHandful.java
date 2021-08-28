package cards.gui.dialogs.events;

import cards.gui.dialogs.DialogTarot;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsMouseListenerRel;

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
