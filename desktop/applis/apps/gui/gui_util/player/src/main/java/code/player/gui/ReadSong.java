package code.player.gui;
import code.gui.events.AbsActionListener;

public final class ReadSong  implements AbsActionListener {

    private final WindowPlayer window;

    public ReadSong(WindowPlayer _window) {
        window = _window;
    }

    @Override
    public void action() {
        window.playOrPause(true);
    }

}
