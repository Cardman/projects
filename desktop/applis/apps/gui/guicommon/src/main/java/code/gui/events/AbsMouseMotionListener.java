package code.gui.events;

import code.gui.AbsMouseButtons;
import code.gui.AbsMouseKeyState;
import code.gui.AbsMouseLocation;

public interface AbsMouseMotionListener {
    void mouseDragged(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons);

    void mouseMoved(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons);
}
