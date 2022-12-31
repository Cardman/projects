package code.mock;

import code.gui.AbsMouseButtons;

public final class MockMouseButtons implements AbsMouseButtons {

    private final boolean left;
    private final boolean middle;
    private final boolean right;
    private final int clickCount;

    public MockMouseButtons(boolean _l, boolean _m, boolean _r, int _c) {
        this.left = _l;
        this.middle = _m;
        this.right = _r;
        this.clickCount = _c;
    }

    @Override
    public boolean isLeftMouseButton() {
        return left;
    }

    @Override
    public boolean isMiddleMouseButton() {
        return middle;
    }

    @Override
    public boolean isRightMouseButton() {
        return right;
    }

    @Override
    public int getClickCount() {
        return clickCount;
    }
}
