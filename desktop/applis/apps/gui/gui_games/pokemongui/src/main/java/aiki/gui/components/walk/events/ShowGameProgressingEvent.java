package aiki.gui.components.walk.events;

import aiki.gui.WindowAiki;
import code.gui.events.AbsActionListener;

public class ShowGameProgressingEvent implements AbsActionListener {

    private WindowAiki window;

    public ShowGameProgressingEvent(WindowAiki _window) {
        window = _window;
    }

    @Override
    public void action() {
        window.showGameProgressing();
    }
}
