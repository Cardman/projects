package cards.gui.events;

import code.gui.events.AbsActionListener;
import code.network.WindowNetWork;

public class ChooseModeMultEvent implements AbsActionListener {

    private final WindowNetWork window;

    public ChooseModeMultEvent(WindowNetWork _window) {
        window = _window;
    }

    @Override
    public void action() {
        window.menuMultiGames();
    }
}
