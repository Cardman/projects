package exportwpl.gui;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SelectFolder extends MouseAdapter {

    private MainWindow window;

    public SelectFolder(MainWindow _window) {
        window = _window;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        window.selectFolder();
    }

}
