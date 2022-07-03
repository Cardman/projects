package code.mock;

import code.gui.AbsCtrlKeyState;
import code.gui.events.AbsAdvActionListener;

public final class MockAdvAction implements AbsAdvActionListener {
    private final int number;
    private final MockWithAdvAction mockWithAdvAction;

    public MockAdvAction(int _nb, MockWithAdvAction _ac) {
        this.number = _nb;
        this.mockWithAdvAction = _ac;
    }
    @Override
    public void action(AbsCtrlKeyState _state, String _command) {
        mockWithAdvAction.action(number,_state,_command);
    }
}
