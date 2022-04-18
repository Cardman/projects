package code.vi.prot.impl.gui.events;

import code.gui.events.AbsMouseListenerWithoutClick;
import code.vi.prot.impl.gui.DefCtrlKeyState;
import code.vi.prot.impl.gui.DefMouseButtons;
import code.vi.prot.impl.gui.DefMouseLocation;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public final class WrMouseListenerWithoutCl extends MouseAdapter {
    private final AbsMouseListenerWithoutClick mouseListener;

    public WrMouseListenerWithoutCl(AbsMouseListenerWithoutClick _mouseListener) {
        this.mouseListener = _mouseListener;
    }

    @Override
    public void mouseEntered(MouseEvent _e) {
        mouseListener.mouseEntered(new DefMouseLocation(_e),new DefCtrlKeyState(_e),new DefMouseButtons(_e));
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        mouseListener.mouseReleased(new DefMouseLocation(_e),new DefCtrlKeyState(_e),new DefMouseButtons(_e));
    }

    @Override
    public void mouseExited(MouseEvent _e) {
        mouseListener.mouseExited(new DefMouseLocation(_e),new DefCtrlKeyState(_e),new DefMouseButtons(_e));
    }

    @Override
    public void mousePressed(MouseEvent _e) {
        mouseListener.mousePressed(new DefMouseLocation(_e),new DefCtrlKeyState(_e),new DefMouseButtons(_e));
    }
}
