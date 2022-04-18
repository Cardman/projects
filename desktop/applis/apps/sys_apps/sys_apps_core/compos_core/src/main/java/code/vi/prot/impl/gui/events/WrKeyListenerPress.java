package code.vi.prot.impl.gui.events;

import code.gui.events.AbsKeyListenerPress;
import code.vi.prot.impl.gui.DefCtrlKeyState;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public final class WrKeyListenerPress extends KeyAdapter {
    private final AbsKeyListenerPress keyListener;

    public WrKeyListenerPress(AbsKeyListenerPress _keyListener) {
        this.keyListener = _keyListener;
    }

    @Override
    public void keyPressed(KeyEvent _e) {
        keyListener.keyPressed(new DefCtrlKeyState(_e), _e.getKeyChar(), _e.getKeyCode());
    }

}
