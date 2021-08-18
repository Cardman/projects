package cards.gui.dialogs.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cards.gui.WindowCards;
import cards.gui.dialogs.SetterSelectedCardList;

public class ValidateRulesDealEvent extends MouseAdapter {

    private SetterSelectedCardList dialog;
    private WindowCards window;

    public ValidateRulesDealEvent(SetterSelectedCardList _dialog, WindowCards _parent) {
        dialog = _dialog;
        window = _parent;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        dialog.validateRulesDeal(window);
    }
}
