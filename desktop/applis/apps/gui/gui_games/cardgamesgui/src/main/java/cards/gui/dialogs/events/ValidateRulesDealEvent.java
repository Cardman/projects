package cards.gui.dialogs.events;

import cards.gui.WindowCardsInt;
import cards.gui.dialogs.SetterSelectedCardList;
import code.gui.events.AbsActionListener;

public class ValidateRulesDealEvent implements AbsActionListener {

    private SetterSelectedCardList dialog;
    private WindowCardsInt window;

    public ValidateRulesDealEvent(SetterSelectedCardList _dialog, WindowCardsInt _parent) {
        dialog = _dialog;
        window = _parent;
    }

    @Override
    public void action() {
        dialog.validateRulesDeal(window);
    }
}
