package code.player.gui;
import code.gui.AbsMouseButtons;
import code.gui.AbsMouseKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerRel;


public class StopSong extends AbsMouseListenerRel {

    private WindowPlayer window;

    public StopSong(WindowPlayer _window) {
        window = _window;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons) {
        window.stopSong();
    }

}
