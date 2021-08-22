package code.gui.events;

import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.AbsMouseWheel;

public interface AbsMouseWheelListener {
    void mouseWheelMoved(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons, AbsMouseWheel _wheel);
}
