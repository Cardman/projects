package code.vi.prot.impl.gui.events;

import code.gui.events.AbsMouseListenerWithoutClick;
import code.vi.prot.impl.gui.DefCtrlKeyState;
import code.vi.prot.impl.gui.DefMouseButtons;
import code.vi.prot.impl.gui.DefMouseLocation;

import java.awt.event.MouseEvent;

public final class WrMouseListenerWithoutCl extends WrMouseListenerEerCom {

    private final AbsMouseListenerWithoutClick adv;
    public WrMouseListenerWithoutCl(AbsMouseListenerWithoutClick _mouseListener) {
        super(_mouseListener);
        adv = _mouseListener;
    }

    @Override
    public void mousePressed(MouseEvent _e) {
        adv.mousePressed(new DefMouseLocation(_e),new DefCtrlKeyState(_e),new DefMouseButtons(_e));
    }
}
