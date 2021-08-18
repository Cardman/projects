package cards.gui.menus;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cards.facade.enumerations.GameEnum;
import cards.gui.WindowCards;

public class ManageRulesEvent implements ActionListener {

    private WindowCards window;

    private GameEnum gameEnum;

    public ManageRulesEvent(WindowCards _window, GameEnum _gameEnum) {
        window = _window;
        gameEnum = _gameEnum;
    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        window.manageRules(gameEnum);
    }
}
