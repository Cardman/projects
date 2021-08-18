package cards.gui.dialogs.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cards.gui.WindowCards;
import cards.gui.dialogs.SetterSelectedCardList;

public class BackToRulesEvent extends MouseAdapter {

    private SetterSelectedCardList dialog;
    private WindowCards window;

    public BackToRulesEvent(SetterSelectedCardList _dialog, WindowCards _window) {
        dialog = _dialog;
        window = _window;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        dialog.backToRules(window);
    }
}
