package code.player.gui;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsMouseListenerRel;


public class StopSong implements AbsActionListener {

    private WindowPlayer window;

    public StopSong(WindowPlayer _window) {
        window = _window;
    }

    @Override
    public void action() {
        window.stopSong();
    }

}
