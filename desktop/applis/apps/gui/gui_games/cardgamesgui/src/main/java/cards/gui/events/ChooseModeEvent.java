package cards.gui.events;

import cards.gui.WindowCards;
import code.gui.events.AbsActionListener;

public class ChooseModeEvent implements AbsActionListener {

    private final WindowCards window;

    public ChooseModeEvent(WindowCards _window) {
        window = _window;
    }

    @Override
    public void action() {
        window.menuSoloGames();
    }
}
