package code.vi.prot.impl.gui.events;

import code.gui.GuiBaseUtil;
import code.gui.events.AbsActionListenerAct;
import code.gui.events.AbsMouseListenerWithoutClickEnter;
import code.vi.prot.impl.gui.DefCtrlKeyState;
import code.vi.prot.impl.gui.DefMouseButtons;
import code.vi.prot.impl.gui.DefMouseLocation;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public final class WrMouseListenerWithoutClEnter extends MouseAdapter {

    private final AbsActionListenerAct act;
    private final AbsMouseListenerWithoutClickEnter adv;
    public WrMouseListenerWithoutClEnter(AbsActionListenerAct _a, AbsMouseListenerWithoutClickEnter _mouseListener) {
        act = _a;
        adv = _mouseListener;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        GuiBaseUtil.action(act,adv,new DefMouseLocation(_e),new DefCtrlKeyState(_e),new DefMouseButtons(_e));
    }

    @Override
    public void mousePressed(MouseEvent _e) {
        GuiBaseUtil.actionPressed(act,adv,new DefMouseLocation(_e),new DefCtrlKeyState(_e),new DefMouseButtons(_e));
    }
}
