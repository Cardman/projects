package code.mock;

import code.gui.events.AbsListSelectionListener;

public final class MockListSelectionListener implements AbsListSelectionListener {
    private int state;
    @Override
    public void valueChanged(int _first, int _last) {
        state = 1;
    }

    public int getState() {
        return state;
    }
}
