package code.gui.events;

import code.gui.DefCtrlKeyState;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public final class WrKeyListener implements KeyListener {
    private final AbsKeyListener keyListener;

    public WrKeyListener(AbsKeyListener _keyListener) {
        this.keyListener = _keyListener;
    }

    @Override
    public void keyTyped(KeyEvent _e) {
        keyListener.keyTyped(new DefCtrlKeyState(_e), _e.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent _e) {
        keyListener.keyPressed(new DefCtrlKeyState(_e), _e.getKeyChar(), _e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent _e) {
        keyListener.keyReleased(new DefCtrlKeyState(_e), _e.getKeyChar(), _e.getKeyCode());
    }
}
