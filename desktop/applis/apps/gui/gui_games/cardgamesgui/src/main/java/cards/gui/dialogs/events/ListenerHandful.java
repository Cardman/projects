package cards.gui.dialogs.events;

import cards.gui.dialogs.DialogTarot;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerRel;

public class ListenerHandful extends AbsMouseListenerRel {

    private DialogTarot dialog;

    public ListenerHandful(DialogTarot _dialog) {
        dialog = _dialog;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        dialog.validateHandfulTrumps();
    }
}
