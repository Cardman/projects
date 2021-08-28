package aiki.gui.components.walk.events;

import aiki.gui.WindowAiki;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsMouseListenerRel;

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
