package code.minirts.events;

import code.gui.events.AbsActionListener;
import code.minirts.WindowRts;

public class Stop implements AbsActionListener {

    private WindowRts window;

    public Stop(WindowRts _window) {
        window = _window;
    }

    @Override
    public void action() {
        window.stopGame();
    }

}
