package code.converterimages.gui;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public final class ExportEvent extends MouseAdapter {

    private MainWindow window;

    public ExportEvent(MainWindow _window) {
        window = _window;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        window.export();
    }
}
