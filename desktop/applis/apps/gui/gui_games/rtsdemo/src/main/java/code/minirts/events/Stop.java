package code.minirts.events;

import code.gui.events.AbsActionListener;
import code.minirts.WindowRts;

public final class Stop implements AbsActionListener {

    private final WindowRts window;

    public Stop(WindowRts _window) {
        window = _window;
    }

    @Override
    public void action() {
        window.stopGame();
    }

}
