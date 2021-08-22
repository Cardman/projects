package cards.gui.dialogs.events;

import cards.gui.dialogs.DialogRules;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerRel;

public class ValidateRulesEvent extends AbsMouseListenerRel {

    private DialogRules dialog;

    public ValidateRulesEvent(DialogRules _dialog) {
        dialog = _dialog;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        dialog.validateRulesClose();
    }
}
