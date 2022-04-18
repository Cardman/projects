package code.gui.events;

import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseButtons;
import code.gui.AbsMouseLocation;

public interface AbsMouseListenerEnt {

    void mouseEntered(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons);
}
