package code.gui.events;

import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseButtons;
import code.gui.AbsMouseLocation;

public interface AbsMouseListenerWithoutClick extends AbsMouseListenerEer,AbsMouseListenerWithoutClickPr,AbsMouseListenerIntRel {

    void mousePressed(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons);

}
