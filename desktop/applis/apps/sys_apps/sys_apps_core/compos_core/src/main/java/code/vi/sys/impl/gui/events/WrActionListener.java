package code.vi.sys.impl.gui.events;

import code.gui.events.AbsActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class WrActionListener implements ActionListener {
    private final AbsActionListener actionListener;

    public WrActionListener(AbsActionListener _actionListener) {
        this.actionListener = _actionListener;
    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        actionListener.action();
    }
}
