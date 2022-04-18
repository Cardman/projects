package cards.gui.dialogs.events;

import cards.gui.dialogs.DialogRules;
import code.gui.events.AbsActionListener;

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
