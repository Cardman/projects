package code.vi.prot.impl.gui;

import code.gui.AbsCtrlKeyState;

import java.awt.event.InputEvent;

public final class DefCtrlKeyState implements AbsCtrlKeyState {
    private final boolean controlDown;
    private final boolean altDown;
    private final boolean shiftDown;

    public DefCtrlKeyState(InputEvent _action) {
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
