package code.expressionlanguage.adv;

import code.gui.events.AbsChangeListener;

public final class RowColStateChangedEvent implements AbsChangeListener {
    private final OutputDialogNavLine dialog;

    public RowColStateChangedEvent(OutputDialogNavLine _d) {
        this.dialog = _d;
    }

    @Override
    public void stateChanged() {
        int index_ = dialog.getTab().index(dialog.getRow().getValue(), dialog.getCol().getValue());
        dialog.getVal().setEnabled(index_ > -1);
        dialog.setIndex(index_);
    }
}
