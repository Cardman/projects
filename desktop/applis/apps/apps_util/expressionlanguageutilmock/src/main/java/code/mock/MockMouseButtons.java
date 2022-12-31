package code.mock;

import code.gui.AbsMouseButtons;

public final class MockMouseButtons implements AbsMouseButtons {

    private final boolean leftMouseButton;
    private final boolean middleMouseButton;
    private final boolean rightMouseButton;
    private final int clickCount;

    public MockMouseButtons(boolean _l, boolean _m, boolean _r, int _c) {
        this.leftMouseButton = _l;
        this.middleMouseButton = _m;
        this.rightMouseButton = _r;
        this.clickCount = _c;
    }

    @Override
    public boolean isLeftMouseButton() {
        return leftMouseButton;
    }

    @Override
    public boolean isMiddleMouseButton() {
        return middleMouseButton;
    }

    @Override
    public boolean isRightMouseButton() {
        return rightMouseButton;
    }

    @Override
    public int getClickCount() {
        return clickCount;
    }
}
