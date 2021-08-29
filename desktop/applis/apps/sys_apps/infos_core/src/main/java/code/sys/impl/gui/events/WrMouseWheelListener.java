package code.sys.impl.gui.events;

import code.sys.impl.gui.DefMouseButtons;
import code.sys.impl.gui.DefCtrlKeyState;
import code.sys.impl.gui.DefMouseLocation;
import code.sys.impl.gui.DefMouseWheel;
import code.gui.events.AbsMouseWheelListener;

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
