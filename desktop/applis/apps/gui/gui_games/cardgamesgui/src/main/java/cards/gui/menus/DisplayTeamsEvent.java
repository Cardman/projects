package cards.gui.menus;

import cards.gui.WindowCards;
import code.gui.events.AbsActionListener;

public class DisplayTeamsEvent implements AbsActionListener {

    private WindowCards window;

    public DisplayTeamsEvent(WindowCards _window) {
        window = _window;
    }

    @Override
    public void action() {
        window.displayTeams();
    }
}
