package code.mock;

import code.gui.AbsMouseWheel;

public final class MockMouseWheel implements AbsMouseWheel {

    private final int wheel;

    public MockMouseWheel(int _c) {
        this.wheel = _c;
    }

    @Override
    public int getWheelRotation() {
        return wheel;
    }
}
