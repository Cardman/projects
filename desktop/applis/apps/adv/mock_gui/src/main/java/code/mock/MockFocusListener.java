package code.mock;

import code.gui.AbsFocusListener;

public final class MockFocusListener implements AbsFocusListener {
    private boolean gained;
    @Override
    public void focusGained() {
        gained = true;
    }

    @Override
    public void focusLost() {
        gained = false;
    }

    public boolean isGained() {
        return gained;
    }
}
