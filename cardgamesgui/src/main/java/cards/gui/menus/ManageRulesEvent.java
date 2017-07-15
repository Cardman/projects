package cards.gui.menus;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cards.facade.enumerations.GameEnum;
import cards.gui.MainWindow;

public class ManageRulesEvent implements ActionListener {

    private MainWindow window;

    private GameEnum gameEnum;

    public ManageRulesEvent(MainWindow _window, GameEnum _gameEnum) {
        window = _window;
        gameEnum = _gameEnum;
    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        window.manageRules(gameEnum);
    }
}
