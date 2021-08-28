package cards.gui.dialogs.events;

import cards.gui.dialogs.DialogDisplaying;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsMouseListenerRel;

public class ValidateDisplayingEvent implements AbsActionListener {

    private DialogDisplaying dialog;

    public ValidateDisplayingEvent(DialogDisplaying _dialog) {
        dialog = _dialog;
    }

    @Override
    public void action() {
        dialog.validateDisplaying();
    }
}
