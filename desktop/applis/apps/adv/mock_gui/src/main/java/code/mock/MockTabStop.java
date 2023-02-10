package code.mock;

import code.gui.AbsTabStop;

public final class MockTabStop implements AbsTabStop {
    private final int value;

    public MockTabStop(int _v) {
        this.value = _v;
    }

    @Override
    public int getValue() {
        return value;
    }
}
