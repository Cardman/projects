package player.gui;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NextSong extends MouseAdapter {

    private MainWindow window;

    public NextSong(MainWindow _window) {
        window = _window;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        window.nextSong();
    }

}
