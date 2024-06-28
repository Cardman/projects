package code.player.gui;
import code.gui.events.AbsActionListener;


public final class StopSong implements AbsActionListener {

    private final WindowPlayer window;

    public StopSong(WindowPlayer _window) {
        window = _window;
    }

    @Override
    public void action() {
        window.stopSong();
    }

}
