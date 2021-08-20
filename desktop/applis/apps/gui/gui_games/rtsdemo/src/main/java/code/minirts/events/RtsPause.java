package code.minirts.events;

import code.gui.events.AbsActionListener;
import code.minirts.WindowRts;

public class RtsPause implements AbsActionListener {

    private final WindowRts window;

    public RtsPause(WindowRts _window) {
        window = _window;
    }

    @Override
    public void action() {
        window.pause();
    }

}
