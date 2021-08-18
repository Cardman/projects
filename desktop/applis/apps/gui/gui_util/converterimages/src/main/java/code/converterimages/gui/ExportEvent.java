package code.converterimages.gui;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public final class ExportEvent extends MouseAdapter {

    private WindowConverter window;

    public ExportEvent(WindowConverter _window) {
        window = _window;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        window.export();
    }
}
