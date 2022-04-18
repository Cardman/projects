package code.gui.events;

import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseButtons;
import code.gui.AbsMouseLocation;

public interface AbsMouseListenerPresRel {

    void mousePressed(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons);

    void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons);

}
