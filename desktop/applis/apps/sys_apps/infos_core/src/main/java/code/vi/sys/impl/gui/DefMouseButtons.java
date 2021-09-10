package code.vi.sys.impl.gui;

import code.gui.AbsMouseButtons;

import javax.swing.*;
import java.awt.event.MouseEvent;

public final class DefMouseButtons implements AbsMouseButtons {
    private final boolean leftMouseButton;
    private final boolean middleMouseButton;
    private final boolean rightMouseButton;
    private final int clickCount;
    public DefMouseButtons(MouseEvent _action) {
        leftMouseButton = SwingUtilities.isLeftMouseButton(_action);
        middleMouseButton = SwingUtilities.isMiddleMouseButton(_action);
        rightMouseButton = SwingUtilities.isRightMouseButton(_action);
        clickCount = _action.getClickCount();
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
