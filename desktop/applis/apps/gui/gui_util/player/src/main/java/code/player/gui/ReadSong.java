package code.player.gui;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ReadSong extends MouseAdapter {

    private MainWindow window;

    public ReadSong(MainWindow _window) {
        window = _window;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        window.playOrPause(true);
    }

}
