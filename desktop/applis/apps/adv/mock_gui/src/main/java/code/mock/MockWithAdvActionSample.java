package code.mock;

import code.gui.AbsCtrlKeyState;

public final class MockWithAdvActionSample implements MockWithAdvAction {
    private boolean act;
    @Override
    public void action(int _nb, AbsCtrlKeyState _state, String _command) {
        act = true;
    }

    public boolean isAct() {
        return act;
    }
}
