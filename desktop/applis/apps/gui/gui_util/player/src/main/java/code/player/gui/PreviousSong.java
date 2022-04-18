package code.player.gui;
import code.gui.events.AbsActionListener;

public class PreviousSong implements AbsActionListener {

    private WindowPlayer window;

    public PreviousSong(WindowPlayer _window) {
        window = _window;
    }

    @Override
    public void action() {
        window.previousSong();
    }

}
