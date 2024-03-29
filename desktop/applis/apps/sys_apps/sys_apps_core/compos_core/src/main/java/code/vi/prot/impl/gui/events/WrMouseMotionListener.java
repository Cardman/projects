package code.vi.prot.impl.gui.events;

import code.gui.events.AbsMouseMotionListener;
import code.vi.prot.impl.gui.DefCtrlKeyState;
import code.vi.prot.impl.gui.DefMouseButtons;
import code.vi.prot.impl.gui.DefMouseLocation;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public final class WrMouseMotionListener implements MouseMotionListener {
    private final AbsMouseMotionListener mouseMotionListener;

    public WrMouseMotionListener(AbsMouseMotionListener _mouseMotionListener) {
        this.mouseMotionListener = _mouseMotionListener;
    }

    @Override
    public void mouseDragged(MouseEvent _e) {
        mouseMotionListener.mouseDragged(new DefMouseLocation(_e),new DefCtrlKeyState(_e),new DefMouseButtons(_e));
    }

    @Override
    public void mouseMoved(MouseEvent _e) {
        mouseMotionListener.mouseMoved(new DefMouseLocation(_e),new DefCtrlKeyState(_e),new DefMouseButtons(_e));
    }
}
