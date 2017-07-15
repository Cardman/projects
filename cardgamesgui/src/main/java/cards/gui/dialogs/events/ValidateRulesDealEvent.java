package cards.gui.dialogs.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cards.gui.dialogs.SetterSelectedCardList;

public class ValidateRulesDealEvent extends MouseAdapter {

    private SetterSelectedCardList dialog;

    public ValidateRulesDealEvent(SetterSelectedCardList _dialog) {
        dialog = _dialog;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        dialog.validateRulesDeal();
    }
}
