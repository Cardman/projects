package cards.gui.menus;

import cards.facade.enumerations.GameEnum;
import cards.gui.WindowCards;
import code.gui.events.AbsActionListener;

public class DisplayingGameEvent implements AbsActionListener {

    private WindowCards window;

    private GameEnum gameEnum;

    public DisplayingGameEvent(WindowCards _window, GameEnum _gameEnum) {
        window = _window;
        gameEnum = _gameEnum;
    }

    @Override
    public void action() {
        window.displayingGame(gameEnum);
    }
}
