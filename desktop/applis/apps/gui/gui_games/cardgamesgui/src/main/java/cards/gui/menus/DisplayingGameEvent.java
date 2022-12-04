package cards.gui.menus;

import cards.facade.enumerations.GameEnum;
import cards.gui.WindowCardsInt;
import code.gui.events.AbsActionListener;

public class DisplayingGameEvent implements AbsActionListener {

    private WindowCardsInt window;

    private GameEnum gameEnum;

    public DisplayingGameEvent(WindowCardsInt _window, GameEnum _gameEnum) {
        window = _window;
        gameEnum = _gameEnum;
    }

    @Override
    public void action() {
        window.displayingGame(gameEnum);
    }
}
