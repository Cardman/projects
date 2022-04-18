package code.vi.prot.impl.gui.events;

import code.gui.events.AbsKeyListenerReleased;
import code.vi.prot.impl.gui.DefCtrlKeyState;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public final class WrKeyListenerRel extends KeyAdapter {
    private final AbsKeyListenerReleased keyListener;

    public WrKeyListenerRel(AbsKeyListenerReleased _keyListener) {
        this.keyListener = _keyListener;
    }

    @Override
    public void keyReleased(KeyEvent _e) {
        keyListener.keyReleased(new DefCtrlKeyState(_e), _e.getKeyChar(), _e.getKeyCode());
    }

}
