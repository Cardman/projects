package code.mock;

import code.gui.events.AbsChangeListener;

public final class MockChangeListener implements AbsChangeListener {
    private final MockWithChangeListener withChangeListener;
    private final int nb;

    public MockChangeListener(MockWithChangeListener _w, int _n) {
        this.withChangeListener = _w;
        this.nb = _n;
    }

    @Override
    public void stateChanged() {
        withChangeListener.action(nb);
    }
}
