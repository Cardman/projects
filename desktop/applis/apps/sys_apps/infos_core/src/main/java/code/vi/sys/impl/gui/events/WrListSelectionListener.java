package code.vi.sys.impl.gui.events;

import code.gui.events.AbsListSelectionListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public final class WrListSelectionListener implements ListSelectionListener {
    private final AbsListSelectionListener listSelectionListener;

    public WrListSelectionListener(AbsListSelectionListener _listSelectionListener) {
        this.listSelectionListener = _listSelectionListener;
    }

    @Override
    public void valueChanged(ListSelectionEvent _e) {
        listSelectionListener.valueChanged(_e.getFirstIndex(), _e.getLastIndex());
    }
}
