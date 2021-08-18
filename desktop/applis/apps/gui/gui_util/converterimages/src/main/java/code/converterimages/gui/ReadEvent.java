package code.converterimages.gui;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public final class ReadEvent extends MouseAdapter {

    private WindowConverter window;

    public ReadEvent(WindowConverter _window) {
        window = _window;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        window.read();
    }
}
