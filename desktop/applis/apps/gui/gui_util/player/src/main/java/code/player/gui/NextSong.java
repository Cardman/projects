package code.player.gui;
import code.gui.events.AbsActionListener;

public final class NextSong implements AbsActionListener {

    private final WindowPlayer window;

    public NextSong(WindowPlayer _window) {
        window = _window;
    }

    @Override
    public void action() {
        window.nextSong();
    }

}
