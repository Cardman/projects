package code.vi.prot.impl.gui.events;

import code.gui.KeyActionEvent;
import code.gui.events.AbsAdvActionListener;
import code.util.core.StringUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class WrAdvActionListener implements ActionListener {
    private final AbsAdvActionListener actionListener;

    public WrAdvActionListener(AbsAdvActionListener _actionListener) {
        this.actionListener = _actionListener;
    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        actionListener.action(new KeyActionEvent(_e.getModifiers()), StringUtil.nullToEmpty(_e.getActionCommand()));
    }
}
