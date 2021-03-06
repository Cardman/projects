package code.player.gui;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PreviousSong extends MouseAdapter {

    private MainWindow window;

    public PreviousSong(MainWindow _window) {
        window = _window;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        window.previousSong();
    }

}
