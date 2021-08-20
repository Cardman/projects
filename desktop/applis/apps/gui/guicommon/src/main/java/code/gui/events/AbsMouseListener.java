package code.gui.events;

import code.gui.AbsMouseButtons;
import code.gui.AbsMouseKeyState;
import code.gui.AbsMouseLocation;

public interface AbsMouseListener {

    void mouseClicked(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons);

    void mousePressed(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons);

    void mouseReleased(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons);

    void mouseEntered(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons);

    void mouseExited(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons);
}
