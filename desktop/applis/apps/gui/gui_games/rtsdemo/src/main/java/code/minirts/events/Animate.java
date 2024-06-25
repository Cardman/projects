package code.minirts.events;

import code.gui.events.AbsActionListener;
import code.minirts.WindowRts;

public final class Animate implements AbsActionListener {

    private final WindowRts window;

    public Animate(WindowRts _window) {
        window = _window;
    }

    @Override
    public void action() {
        window.animate();
    }

}
