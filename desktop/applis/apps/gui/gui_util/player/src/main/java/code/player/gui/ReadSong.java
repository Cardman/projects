package code.player.gui;
import code.gui.AbsMouseButtons;
import code.gui.AbsMouseKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerRel;

public class ReadSong extends AbsMouseListenerRel {

    private WindowPlayer window;

    public ReadSong(WindowPlayer _window) {
        window = _window;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons) {
        window.playOrPause(true);
    }

}
