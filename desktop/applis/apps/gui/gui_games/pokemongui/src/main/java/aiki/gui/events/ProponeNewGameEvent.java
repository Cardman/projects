package aiki.gui.events;

import aiki.gui.WindowAiki;
import code.gui.events.AbsActionListener;

public class ProponeNewGameEvent implements AbsActionListener {

    private WindowAiki window;

    public ProponeNewGameEvent(WindowAiki _window) {
        window = _window;
    }

    @Override
    public void action() {
        window.proponeNewGame();
    }

}
