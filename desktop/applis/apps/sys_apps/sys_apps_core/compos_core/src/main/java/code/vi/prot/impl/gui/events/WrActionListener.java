package code.vi.prot.impl.gui.events;

import code.gui.GuiBaseUtil;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsActionListenerAct;
import code.gui.events.AlwaysActionListenerAct;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class WrActionListener implements ActionListener {
    private final AbsActionListenerAct actionListenerCond;
    private final AbsActionListener actionListener;

    public WrActionListener(AbsActionListener _actionListener) {
        this(new AlwaysActionListenerAct(),_actionListener);
    }

    public WrActionListener(AbsActionListenerAct _c,AbsActionListener _actionListener) {
        this.actionListenerCond = _c;
        this.actionListener = _actionListener;
    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        GuiBaseUtil.action(actionListenerCond,actionListener);
    }
}
