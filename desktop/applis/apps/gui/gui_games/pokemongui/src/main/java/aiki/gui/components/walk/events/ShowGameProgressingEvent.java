package aiki.gui.components.walk.events;

import aiki.gui.WindowAiki;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerRel;

public class ShowGameProgressingEvent extends AbsMouseListenerRel {

    private WindowAiki window;

    public ShowGameProgressingEvent(WindowAiki _window) {
        window = _window;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        window.showGameProgressing();
    }
}
