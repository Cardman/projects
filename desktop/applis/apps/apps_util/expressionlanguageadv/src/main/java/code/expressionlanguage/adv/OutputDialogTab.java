package code.expressionlanguage.adv;

import code.gui.AbsPanel;
import code.gui.AbsPlainButton;
import code.gui.AbsSpinner;
import code.gui.initialize.AbstractProgramInfos;
import code.threads.AbstractAtomicBoolean;

public final class OutputDialogTab {
    private final WindowCdmEditor windowCdmEditor;
    private final AbstractAtomicBoolean valid;
    private AbsPlainButton val;
    private AbsPlainButton cancel;
    private AbsSpinner tabulation;

    public OutputDialogTab(WindowCdmEditor _w) {
        windowCdmEditor = _w;
        AbstractProgramInfos factories_ = windowCdmEditor.getCommonFrame().getFrames();
        valid = factories_.getThreadFactory().newAtomicBoolean();
    }
    public void update() {
        AbstractProgramInfos factories_ = windowCdmEditor.getCommonFrame().getFrames();
        AbsPanel all_ = factories_.getCompoFactory().newPageBox();
        tabulation = factories_.getCompoFactory().newSpinner(windowCdmEditor.getTabWidth(),1,64,1);
        all_.add(tabulation);
        val = factories_.getCompoFactory().newPlainButton("OK");
        val.addActionListener(new ValidateTabulations(valid,tabulation,windowCdmEditor));
        all_.add(val);
        cancel = factories_.getCompoFactory().newPlainButton("KO");
        cancel.addActionListener(new CancelBasic(windowCdmEditor.getDialogTabulations()));
        all_.add(cancel);
        windowCdmEditor.getDialogTabulations().setContentPane(all_);
        windowCdmEditor.getDialogTabulations().pack();
        windowCdmEditor.getDialogTabulations().setVisible(true);
    }

    public AbstractAtomicBoolean getValid() {
        return valid;
    }

    public AbsSpinner getTabulation() {
        return tabulation;
    }

    public AbsPlainButton getCancel() {
        return cancel;
    }


    public AbsPlainButton getVal() {
        return val;
    }
}
