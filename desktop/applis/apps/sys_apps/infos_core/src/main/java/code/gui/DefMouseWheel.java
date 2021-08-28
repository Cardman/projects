package code.gui;

import java.awt.event.MouseWheelEvent;

public final class DefMouseWheel implements AbsMouseWheel {
    private final int wheelRotation;
    public DefMouseWheel(MouseWheelEvent _action) {
        wheelRotation = _action.getWheelRotation();
    }

    @Override
    public int getWheelRotation() {
        return wheelRotation;
    }
}
