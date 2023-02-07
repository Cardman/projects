package code.mock;

import code.gui.AbsCtrlKeyState;
import code.gui.events.AbsAdvActionListener;

public final class MockAdvAbstractAction extends MockAbstractActionCommon implements AbsAdvActionListener {

    private final AbsAdvActionListener actionListener;

    public MockAdvAbstractAction(AbsAdvActionListener _a) {
        this.actionListener = _a;
    }

    @Override
    public void action(AbsCtrlKeyState _state, String _command) {
        if (!isEnabled()) {
            return;
        }
        actionListener.action(_state, _command);
    }
}
