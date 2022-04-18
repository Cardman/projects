package code.vi.prot.impl.gui.events;

import code.gui.events.AbsMouseListenerEnt;
import code.vi.prot.impl.gui.DefCtrlKeyState;
import code.vi.prot.impl.gui.DefMouseButtons;
import code.vi.prot.impl.gui.DefMouseLocation;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public final class WrMouseListenerEnt extends MouseAdapter {
    private final AbsMouseListenerEnt mouseListener;

    public WrMouseListenerEnt(AbsMouseListenerEnt _mouseListener) {
        this.mouseListener = _mouseListener;
    }

    @Override
    public void mouseEntered(MouseEvent _e) {
        mouseListener.mouseEntered(new DefMouseLocation(_e),new DefCtrlKeyState(_e),new DefMouseButtons(_e));
    }

}
