package code.gui;

import java.awt.event.MouseEvent;

public final class DefMouseKeyState implements AbsMouseKeyState {
    private final boolean controlDown;
    private final boolean altDown;
    private final boolean shiftDown;

    public DefMouseKeyState(MouseEvent _action) {
        controlDown = _action.isControlDown();
        altDown = _action.isAltDown();
        shiftDown = _action.isShiftDown();
    }

    @Override
    public boolean isControlDown() {
        return controlDown;
    }

    @Override
    public boolean isAltDown() {
        return altDown;
    }

    @Override
    public boolean isShiftDown() {
        return shiftDown;
    }
}
