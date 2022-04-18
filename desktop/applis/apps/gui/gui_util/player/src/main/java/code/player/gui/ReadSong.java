package code.player.gui;
import code.gui.events.AbsActionListener;

public class ReadSong  implements AbsActionListener {

    private WindowPlayer window;

    public ReadSong(WindowPlayer _window) {
        window = _window;
    }

    @Override
    public void action() {
        window.playOrPause(true);
    }

}
