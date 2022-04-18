package cards.gui.dialogs.events;

import cards.gui.WindowCards;
import cards.gui.dialogs.SetterSelectedCardList;
import code.gui.events.AbsActionListener;

public class BackToRulesEvent implements AbsActionListener {

    private SetterSelectedCardList dialog;
    private WindowCards window;

    public BackToRulesEvent(SetterSelectedCardList _dialog, WindowCards _window) {
        dialog = _dialog;
        window = _window;
    }

    @Override
    public void action() {
        dialog.backToRules(window);
    }
}
