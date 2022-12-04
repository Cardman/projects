package cards.gui.menus;

import cards.gui.WindowCardsInt;
import code.gui.events.AbsActionListener;

public class DisplayTeamsEvent implements AbsActionListener {

    private WindowCardsInt window;

    public DisplayTeamsEvent(WindowCardsInt _window) {
        window = _window;
    }

    @Override
    public void action() {
        window.displayTeams();
    }
}
