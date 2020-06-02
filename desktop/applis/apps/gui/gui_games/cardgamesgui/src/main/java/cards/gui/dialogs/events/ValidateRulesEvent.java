package cards.gui.dialogs.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cards.gui.dialogs.DialogRules;

public class ValidateRulesEvent extends MouseAdapter {

    private DialogRules dialog;

    public ValidateRulesEvent(DialogRules _dialog) {
        dialog = _dialog;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        dialog.validateRulesClose();
    }
}
