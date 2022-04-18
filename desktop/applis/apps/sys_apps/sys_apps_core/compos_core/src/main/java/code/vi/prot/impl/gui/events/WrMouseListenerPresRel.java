package code.vi.prot.impl.gui.events;

import code.gui.events.AbsMouseListenerPresRel;
import code.vi.prot.impl.gui.DefCtrlKeyState;
import code.vi.prot.impl.gui.DefMouseButtons;
import code.vi.prot.impl.gui.DefMouseLocation;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public final class WrMouseListenerPresRel extends MouseAdapter {
    private final AbsMouseListenerPresRel mouseListener;

    public WrMouseListenerPresRel(AbsMouseListenerPresRel _mouseListener) {
        this.mouseListener = _mouseListener;
    }

    @Override
    public void mousePressed(MouseEvent _e) {
        mouseListener.mousePressed(new DefMouseLocation(_e),new DefCtrlKeyState(_e),new DefMouseButtons(_e));
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        mouseListener.mouseReleased(new DefMouseLocation(_e),new DefCtrlKeyState(_e),new DefMouseButtons(_e));
    }

}
