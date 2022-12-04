package cards.gui.dialogs.events;

import cards.gui.WindowCardsInt;
import cards.gui.dialogs.SetterSelectedCardList;
import code.gui.events.AbsActionListener;

public class BackToRulesEvent implements AbsActionListener {

    private SetterSelectedCardList dialog;
    private WindowCardsInt window;

    public BackToRulesEvent(SetterSelectedCardList _dialog, WindowCardsInt _window) {
        dialog = _dialog;
        window = _window;
    }

    @Override
    public void action() {
        dialog.backToRules(window);
    }
}
