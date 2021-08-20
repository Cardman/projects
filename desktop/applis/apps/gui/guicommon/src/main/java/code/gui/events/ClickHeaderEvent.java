package code.gui.events;
import code.gui.AbsMouseButtons;
import code.gui.AbsMouseKeyState;
import code.gui.AbsMouseLocation;
import code.gui.FileDialog;

public class ClickHeaderEvent implements AbsMouseListener {

    private FileDialog dialog;

    public ClickHeaderEvent(FileDialog _dialog) {
        dialog = _dialog;
    }

    @Override
    public void mouseClicked(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons) {
        dialog.clickHeader(_location);
    }

    @Override
    public void mousePressed(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons) {
        //
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons) {
        //
    }

    @Override
    public void mouseEntered(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons) {
        //
    }

    @Override
    public void mouseExited(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons) {
        //
    }

}
