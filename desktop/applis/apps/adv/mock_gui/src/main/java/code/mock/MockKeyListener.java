package code.mock;

import code.gui.AbsCtrlKeyState;
import code.gui.events.AbsKeyListener;
import code.gui.events.AbsKeyListenerPress;
import code.gui.events.AbsKeyListenerReleased;

public final class MockKeyListener implements AbsKeyListenerPress, AbsKeyListenerReleased, AbsKeyListener {
    private int state;
    @Override
    public void keyTyped(AbsCtrlKeyState _keyState, char _keyChar) {
        state = 1;
    }

    @Override
    public void keyPressed(AbsCtrlKeyState _keyState, char _keyChar, int _keyCode) {
        state = 2;
    }

    @Override
    public void keyReleased(AbsCtrlKeyState _keyState, char _keyChar, int _keyCode) {
        state = 3;
    }

    public int getState() {
        return state;
    }
}
