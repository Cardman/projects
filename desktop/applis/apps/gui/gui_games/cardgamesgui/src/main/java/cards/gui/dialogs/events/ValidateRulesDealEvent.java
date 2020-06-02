package cards.gui.dialogs.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cards.gui.MainWindow;
import cards.gui.dialogs.SetterSelectedCardList;

public class ValidateRulesDealEvent extends MouseAdapter {

    private SetterSelectedCardList dialog;
    private MainWindow window;

    public ValidateRulesDealEvent(SetterSelectedCardList _dialog, MainWindow _parent) {
        dialog = _dialog;
        window = _parent;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        dialog.validateRulesDeal(window);
    }
}
