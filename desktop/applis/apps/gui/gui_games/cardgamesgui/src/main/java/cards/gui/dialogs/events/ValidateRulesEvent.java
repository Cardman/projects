package cards.gui.dialogs.events;

import cards.gui.dialogs.DialogRules;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsMouseListenerRel;

public class ValidateRulesEvent implements AbsActionListener {

    private DialogRules dialog;

    public ValidateRulesEvent(DialogRules _dialog) {
        dialog = _dialog;
    }

    @Override
    public void action() {
        dialog.validateRulesClose();
    }
}
