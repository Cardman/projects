package cards.gui.menus;

import cards.facade.enumerations.GameEnum;
import cards.gui.WindowCards;
import code.gui.AbsActionListener;

public class EditEvent implements AbsActionListener {

    private WindowCards window;

    private GameEnum gameEnum;

    public EditEvent(WindowCards _window, GameEnum _gameEnum) {
        window = _window;
        gameEnum = _gameEnum;
    }

    @Override
    public void action() {
        window.editGame(gameEnum);
    }
}
