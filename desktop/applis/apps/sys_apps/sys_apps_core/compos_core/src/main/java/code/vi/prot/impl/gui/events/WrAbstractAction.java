package code.vi.prot.impl.gui.events;

import code.gui.events.AbsEnabledAction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class WrAbstractAction extends AbstractAction implements AbsEnabledAction {
    private final transient ActionListener actionListener;

    public WrAbstractAction(ActionListener _ac) {
        this.actionListener = _ac;
    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        actionListener.actionPerformed(_e);
    }
}
