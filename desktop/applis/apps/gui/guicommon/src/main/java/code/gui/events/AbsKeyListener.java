package code.gui.events;

import code.gui.AbsCtrlKeyState;

public interface AbsKeyListener extends AbsKeyListenerPress,AbsKeyListenerReleased {
    void keyTyped(AbsCtrlKeyState _keyState, char _keyChar);
}
