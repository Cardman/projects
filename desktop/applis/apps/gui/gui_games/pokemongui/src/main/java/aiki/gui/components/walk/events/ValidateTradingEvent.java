package aiki.gui.components.walk.events;

import aiki.gui.WindowAiki;
import aiki.network.stream.Ok;
import code.gui.AbsMouseButtons;
import code.gui.AbsMouseKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerRel;

public class ValidateTradingEvent extends AbsMouseListenerRel {

    private WindowAiki window;

    public ValidateTradingEvent(WindowAiki _window) {
        window = _window;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons) {
        window.sendObject(Ok.INSTANCE);
    }
}
