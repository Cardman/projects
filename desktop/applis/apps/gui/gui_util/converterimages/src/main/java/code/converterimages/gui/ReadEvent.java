package code.converterimages.gui;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsMouseListenerRel;

public final class ReadEvent implements AbsActionListener {

    private WindowConverter window;

    public ReadEvent(WindowConverter _window) {
        window = _window;
    }

    @Override
    public void action() {
        window.read();
    }
}
