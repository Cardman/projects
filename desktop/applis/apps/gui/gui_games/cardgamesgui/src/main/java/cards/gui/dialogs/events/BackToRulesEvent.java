package cards.gui.dialogs.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cards.gui.MainWindow;
import cards.gui.dialogs.SetterSelectedCardList;

public class BackToRulesEvent extends MouseAdapter {

    private SetterSelectedCardList dialog;
    private MainWindow window;

    public BackToRulesEvent(SetterSelectedCardList _dialog, MainWindow _window) {
        dialog = _dialog;
        window = _window;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        dialog.backToRules(window);
    }
}
