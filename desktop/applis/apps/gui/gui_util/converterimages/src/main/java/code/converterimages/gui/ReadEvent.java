package code.converterimages.gui;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerRel;

public final class ReadEvent extends AbsMouseListenerRel {

    private WindowConverter window;

    public ReadEvent(WindowConverter _window) {
        window = _window;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        window.read();
    }
}
