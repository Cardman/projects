package code.gui.events;

import code.gui.DefMouseButtons;
import code.gui.DefCtrlKeyState;
import code.gui.DefMouseLocation;
import code.gui.DefMouseWheel;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public final class WrMouseWheelListener implements MouseWheelListener {
    private final AbsMouseWheelListener wheelListener;

    public WrMouseWheelListener(AbsMouseWheelListener _wheelListener) {
        this.wheelListener = _wheelListener;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent _e) {
        wheelListener.mouseWheelMoved(new DefMouseLocation(_e),new DefCtrlKeyState(_e),new DefMouseButtons(_e),new DefMouseWheel(_e));
    }
}
