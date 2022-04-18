package code.vi.prot.impl.gui.events;

import code.gui.events.AbsMouseListenerCl;
import code.vi.prot.impl.gui.DefCtrlKeyState;
import code.vi.prot.impl.gui.DefMouseButtons;
import code.vi.prot.impl.gui.DefMouseLocation;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public final class WrMouseListenerCl extends MouseAdapter {
    private final AbsMouseListenerCl mouseListener;

    public WrMouseListenerCl(AbsMouseListenerCl _mouseListener) {
        this.mouseListener = _mouseListener;
    }

    @Override
    public void mouseClicked(MouseEvent _e) {
        mouseListener.mouseClicked(new DefMouseLocation(_e),new DefCtrlKeyState(_e),new DefMouseButtons(_e));
    }

}
