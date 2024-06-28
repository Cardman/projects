package code.player.gui;
import code.gui.events.AbsActionListener;

public final class PreviousSong implements AbsActionListener {

    private final WindowPlayer window;

    public PreviousSong(WindowPlayer _window) {
        window = _window;
    }

    @Override
    public void action() {
        window.previousSong();
    }

}
