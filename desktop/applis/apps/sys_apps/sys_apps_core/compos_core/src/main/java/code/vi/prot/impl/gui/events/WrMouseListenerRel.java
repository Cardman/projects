package code.vi.prot.impl.gui.events;

import code.gui.GuiBaseUtil;
import code.gui.events.AbsActionListenerAct;
import code.gui.events.AbsMouseListenerIntRel;
import code.gui.events.AlwaysActionListenerAct;
import code.vi.prot.impl.gui.DefCtrlKeyState;
import code.vi.prot.impl.gui.DefMouseButtons;
import code.vi.prot.impl.gui.DefMouseLocation;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public final class WrMouseListenerRel extends MouseAdapter {
    private final AbsMouseListenerIntRel mouseListener;

    private final AbsActionListenerAct actionListenerCond;

    public WrMouseListenerRel(AbsMouseListenerIntRel _actionListener) {
        this(new AlwaysActionListenerAct(),_actionListener);
    }

    public WrMouseListenerRel(AbsActionListenerAct _c,AbsMouseListenerIntRel _mouseListener) {
        this.actionListenerCond = _c;
        this.mouseListener = _mouseListener;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        GuiBaseUtil.action(actionListenerCond,mouseListener,new DefMouseLocation(_e),new DefCtrlKeyState(_e),new DefMouseButtons(_e));
    }

}
