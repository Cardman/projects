package code.expressionlanguage.adv;

import code.gui.AbsPanel;
import code.gui.AbsPlainButton;
import code.gui.AbsSpinner;
import code.gui.initialize.AbstractProgramInfos;
import code.threads.AbstractAtomicBoolean;

public final class OutputDialogNavLine {
    private final TabEditor tab;
    private final WindowCdmEditor windowCdmEditor;
    private final AbstractAtomicBoolean valid;
    private int index=-1;
    private AbsPlainButton val;
    private AbsPlainButton cancel;
    private AbsSpinner row;
    private AbsSpinner col;

    public OutputDialogNavLine(TabEditor _w) {
        tab = _w;
        windowCdmEditor = _w.getWindowEditor();
        AbstractProgramInfos factories_ = windowCdmEditor.getCommonFrame().getFrames();
        valid = factories_.getThreadFactory().newAtomicBoolean();
    }
    public void update() {
        AbstractProgramInfos factories_ = windowCdmEditor.getCommonFrame().getFrames();
        AbsPanel all_ = factories_.getCompoFactory().newPageBox();
        row = factories_.getCompoFactory().newSpinner(1, 1, Integer.MAX_VALUE, 1);
        row.addChangeListener(new RowColStateChangedEvent(this));
        all_.add(row);
        col = factories_.getCompoFactory().newSpinner(1, 1, Integer.MAX_VALUE, 1);
        col.addChangeListener(new RowColStateChangedEvent(this));
        all_.add(col);
        val = factories_.getCompoFactory().newPlainButton("OK");
        val.addActionListener(new ValidateNavLine(this));
        all_.add(val);
        cancel = factories_.getCompoFactory().newPlainButton("KO");
        cancel.addActionListener(new CancelBasic(windowCdmEditor.getDialogNavigLine()));
        all_.add(cancel);
        windowCdmEditor.getDialogNavigLine().setContentPane(all_);
        windowCdmEditor.getDialogNavigLine().pack();
        windowCdmEditor.getDialogNavigLine().setVisible(true);
    }

    public AbstractAtomicBoolean getValid() {
        return valid;
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

    public WindowCdmEditor getWindowCdmEditor() {
        return windowCdmEditor;
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
