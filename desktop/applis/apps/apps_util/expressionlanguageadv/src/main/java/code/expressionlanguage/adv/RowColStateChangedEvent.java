package code.expressionlanguage.adv;

import code.gui.AbsSpinner;
import code.gui.events.AbsChangeListener;

public final class RowColStateChangedEvent implements AbsChangeListener {
    private final TabEditor tab;
    private final AbsSpinner row;
    private final AbsSpinner col;

    public RowColStateChangedEvent(TabEditor _t, AbsSpinner _r, AbsSpinner _c) {
        tab = _t;
        row = _r;
        col = _c;
    }

    @Override
    public void stateChanged() {
        int index_ = tab.index(row.getValue(), col.getValue());
        tab.setIndex(index_);
//        tab.getVal().setEnabled(index_ > -1);
    }
}
