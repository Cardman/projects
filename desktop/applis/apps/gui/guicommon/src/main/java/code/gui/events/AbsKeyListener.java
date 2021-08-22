package code.gui.events;

import code.gui.AbsCtrlKeyState;

public interface AbsKeyListener {
    void keyTyped(AbsCtrlKeyState _keyState, char _keyChar);

    void keyPressed(AbsCtrlKeyState _keyState, char _keyChar, int _keyCode);

    void keyReleased(AbsCtrlKeyState _keyState, char _keyChar, int _keyCode);
}
