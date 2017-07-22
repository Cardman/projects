package exportwpl.gui;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SelectFile extends MouseAdapter {

    private MainWindow window;

    public SelectFile(MainWindow _window) {
        window = _window;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        window.selectFile();
    }
}
