package aiki.gui.events;

import aiki.gui.WindowAiki;
import code.gui.AbsMouseButtons;
import code.gui.AbsMouseKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerRel;

public class ConfirmNewGameEvent extends AbsMouseListenerRel {

    private WindowAiki window;

    public ConfirmNewGameEvent(WindowAiki _window) {
        window = _window;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons) {
        window.confirmNewGame();
    }
}
