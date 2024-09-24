package cards.gui.containers.events;

import cards.gui.WindowCards;
import code.gui.events.AbsActionListener;

public class StopPlayingEvent implements AbsActionListener {

    private final WindowCards container;

    public StopPlayingEvent(WindowCards _container) {
        container = _container;
    }

    @Override
    public void action() {
        container.menuSoloGames();
    }
}
