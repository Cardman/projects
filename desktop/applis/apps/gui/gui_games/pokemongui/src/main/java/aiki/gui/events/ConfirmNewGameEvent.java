package aiki.gui.events;

import aiki.gui.WindowAiki;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsMouseListenerRel;

public class ConfirmNewGameEvent implements AbsActionListener {

    private WindowAiki window;

    public ConfirmNewGameEvent(WindowAiki _window) {
        window = _window;
    }

    @Override
    public void action() {
        window.confirmNewGame();
    }
}
