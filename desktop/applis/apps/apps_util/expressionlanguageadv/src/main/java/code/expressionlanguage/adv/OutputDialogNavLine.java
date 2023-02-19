package code.expressionlanguage.adv;

import code.gui.AbsPanel;
import code.gui.AbsPlainButton;
import code.gui.AbsSpinner;
import code.gui.initialize.AbstractProgramInfos;

public final class OutputDialogNavLine {
    private final TabEditor tab;
    private int index=-1;
    private final AbsPlainButton val;
    private final AbsPlainButton cancel;
    private final AbsSpinner row;
    private final AbsSpinner col;

    public OutputDialogNavLine(TabEditor _w) {
        tab = _w;
        WindowCdmEditor mainFr_ = _w.getWindowEditor();
        AbstractProgramInfos factories_ = mainFr_.getCommonFrame().getFrames();
        AbsPanel all_ = factories_.getCompoFactory().newPageBox();
        row = factories_.getCompoFactory().newSpinner(1, 1, Integer.MAX_VALUE, 1);
        row.addChangeListener(new RowColStateChangedEvent(this));
        all_.add(row);
        col = factories_.getCompoFactory().newSpinner(1, 1, Integer.MAX_VALUE, 1);
        col.addChangeListener(new RowColStateChangedEvent(this));
        all_.add(col);
        val = factories_.getCompoFactory().newPlainButton("OK");
        val.addActionListener(new ValidateNavLine(this, mainFr_));
        all_.add(val);
        cancel = factories_.getCompoFactory().newPlainButton("KO");
        cancel.addActionListener(new CancelBasic(mainFr_.getDialogNavigLine()));
        all_.add(cancel);
        mainFr_.getDialogNavigLine().setContentPane(all_);
        mainFr_.getDialogNavigLine().pack();
        mainFr_.getDialogNavigLine().setVisible(true);
    }

    public TabEditor getTab() {
        return tab;
    }

    public AbsSpinner getCol() {
        return col;
    }

    public AbsSpinner getRow() {
        return row;
    }

    public AbsPlainButton getCancel() {
        return cancel;
    }


    public AbsPlainButton getVal() {
        return val;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int _i) {
        this.index = _i;
    }
}
