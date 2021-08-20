package cards.gui.dialogs.events;

import cards.gui.dialogs.DialogDisplaying;
import code.gui.AbsMouseButtons;
import code.gui.AbsMouseKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerRel;

public class AddSuitEvent extends AbsMouseListenerRel {

    private DialogDisplaying dialog;

    public AddSuitEvent(DialogDisplaying _dialog) {
        dialog = _dialog;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons) {
        dialog.addSuit();
    }
}
