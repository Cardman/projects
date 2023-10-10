package code.mock;

import code.gui.ListSelection;
import code.gui.SelectionInfo;

public final class MockListSampleSelection implements ListSelection {
    private int state;

    @Override
    public void valueChanged(SelectionInfo _e) {
        state = 1;
    }

    public int getState() {
        return state;
    }
}
