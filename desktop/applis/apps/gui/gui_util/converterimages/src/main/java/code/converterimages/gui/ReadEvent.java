package code.converterimages.gui;
import code.gui.events.AbsActionListener;

public final class ReadEvent implements AbsActionListener {

    private final WindowConverter window;

    public ReadEvent(WindowConverter _window) {
        window = _window;
    }

    @Override
    public void action() {
        window.read();
    }
}
