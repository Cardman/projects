package code.gui.events;

import code.gui.DefMouseButtons;
import code.gui.DefCtrlKeyState;
import code.gui.DefMouseLocation;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public final class WrMouseListener implements MouseListener {
    private final AbsMouseListener mouseListener;

    public WrMouseListener(AbsMouseListener _mouseListener) {
        this.mouseListener = _mouseListener;
    }

    @Override
    public void mouseClicked(MouseEvent _e) {
        mouseListener.mouseClicked(new DefMouseLocation(_e),new DefCtrlKeyState(_e),new DefMouseButtons(_e));
    }

    @Override
    public void mousePressed(MouseEvent _e) {
        mouseListener.mousePressed(new DefMouseLocation(_e),new DefCtrlKeyState(_e),new DefMouseButtons(_e));
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        mouseListener.mouseReleased(new DefMouseLocation(_e),new DefCtrlKeyState(_e),new DefMouseButtons(_e));
    }

    @Override
    public void mouseEntered(MouseEvent _e) {
        mouseListener.mouseEntered(new DefMouseLocation(_e),new DefCtrlKeyState(_e),new DefMouseButtons(_e));
    }

    @Override
    public void mouseExited(MouseEvent _e) {
        mouseListener.mouseExited(new DefMouseLocation(_e),new DefCtrlKeyState(_e),new DefMouseButtons(_e));
    }
}
