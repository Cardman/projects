package code.player.gui;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StopSong extends MouseAdapter {

    private MainWindow window;

    public StopSong(MainWindow _window) {
        window = _window;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        window.stopSong();
    }

}
