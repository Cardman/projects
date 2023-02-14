package code.expressionlanguage.adv;

import code.gui.AbsPanel;
import code.gui.AbsPlainButton;
import code.gui.AbsSpinner;
import code.gui.initialize.AbstractProgramInfos;

public final class OutputDialogNavLine {
    private final TabEditor tab;
    private final WindowCdmEditor windowCdmEditor;
    private final OutputDialogNavLineResult result;
    private AbsPlainButton val;
    private AbsPlainButton cancel;
    private AbsSpinner row;
    private AbsSpinner col;

    public OutputDialogNavLine(TabEditor _w) {
        tab = _w;
        windowCdmEditor = _w.getWindowEditor();
        AbstractProgramInfos factories_ = windowCdmEditor.getCommonFrame().getFrames();
        result = new OutputDialogNavLineResult(factories_.getThreadFactory().newAtomicBoolean());
    }
    public void update() {
        AbstractProgramInfos factories_ = windowCdmEditor.getCommonFrame().getFrames();
        AbsPanel all_ = factories_.getCompoFactory().newPageBox();
        row = factories_.getCompoFactory().newSpinner(1, 1, Integer.MAX_VALUE, 1);
        all_.add(row);
        col = factories_.getCompoFactory().newSpinner(1, 1, Integer.MAX_VALUE, 1);
        all_.add(col);
        val = factories_.getCompoFactory().newPlainButton("OK");
        val.addActionListener(new ValidateNavLine(this));
        all_.add(val);
        cancel = factories_.getCompoFactory().newPlainButton("KO");
        cancel.addActionListener(new CancelNavLine(windowCdmEditor));
        all_.add(cancel);
        windowCdmEditor.getDialogNavigLine().setContentPane(all_);
        windowCdmEditor.getDialogNavigLine().pack();
        windowCdmEditor.getDialogNavigLine().setVisible(true);
    }

    public OutputDialogNavLineResult getResult() {
        return result;
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
}
