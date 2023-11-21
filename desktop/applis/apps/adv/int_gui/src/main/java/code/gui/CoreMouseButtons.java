package code.gui;


public class CoreMouseButtons implements AbsMouseButtons{

    private final boolean leftMouseButton;
    private final boolean middleMouseButton;
    private final boolean rightMouseButton;
    private final int clickCount;
    public CoreMouseButtons(boolean _l, boolean _m, boolean _r, int _c) {
        leftMouseButton = _l;
        middleMouseButton = _m;
        rightMouseButton = _r;
        clickCount = _c;
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
