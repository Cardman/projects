package code.mock;

import code.gui.events.AbsWindowListener;
import code.gui.events.AbsWindowListenerClosing;

public final class MockWindowListener implements AbsWindowListener, AbsWindowListenerClosing {
    private int state;
    @Override
    public void windowOpened() {
        state = 1;
    }

    @Override
    public void windowClosing() {
        state = 2;
    }

    @Override
    public void windowClosed() {
        state = 3;
    }

    @Override
    public void windowIconified() {
        state = 4;
    }

    @Override
    public void windowDeiconified() {
        state = 5;
    }

    @Override
    public void windowActivated() {
        state = 6;
    }

    @Override
    public void windowDeactivated() {
        state = 7;
    }

    public int getState() {
        return state;
    }
}
