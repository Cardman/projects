package code.minirts.events;

import code.gui.events.AbsActionListener;
import code.minirts.WindowRts;

public class Animate implements AbsActionListener {

    private WindowRts window;

    public Animate(WindowRts _window) {
        window = _window;
    }

    @Override
    public void action() {
        window.animate();
    }

}
