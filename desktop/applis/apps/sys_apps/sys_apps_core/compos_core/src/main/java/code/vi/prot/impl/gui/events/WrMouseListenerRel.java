package code.vi.prot.impl.gui.events;

import code.gui.events.AbsMouseListenerIntRel;
import code.vi.prot.impl.gui.DefCtrlKeyState;
import code.vi.prot.impl.gui.DefMouseButtons;
import code.vi.prot.impl.gui.DefMouseLocation;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public final class WrMouseListenerRel extends MouseAdapter {
    private final AbsMouseListenerIntRel mouseListener;

    public WrMouseListenerRel(AbsMouseListenerIntRel _mouseListener) {
        this.mouseListener = _mouseListener;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        mouseListener.mouseReleased(new DefMouseLocation(_e),new DefCtrlKeyState(_e),new DefMouseButtons(_e));
    }

}
