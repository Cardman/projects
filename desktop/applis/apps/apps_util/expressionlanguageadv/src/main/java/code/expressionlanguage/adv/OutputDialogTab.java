package code.expressionlanguage.adv;

import code.gui.AbsPanel;
import code.gui.AbsPlainButton;
import code.gui.AbsSpinner;
import code.gui.initialize.AbstractProgramInfos;

public final class OutputDialogTab {
    private final AbsPlainButton val;
    private final AbsPlainButton cancel;
    private final AbsSpinner tabulation;

    public OutputDialogTab(WindowCdmEditor _w) {
        AbstractProgramInfos factories_ = _w.getCommonFrame().getFrames();
        AbsPanel all_ = factories_.getCompoFactory().newPageBox();
        tabulation = factories_.getCompoFactory().newSpinner(_w.getTabWidth(),1,64,1);
        all_.add(tabulation);
        val = factories_.getCompoFactory().newPlainButton("OK");
        val.addActionListener(new ValidateTabulations(tabulation,_w));
        all_.add(val);
        cancel = factories_.getCompoFactory().newPlainButton("KO");
        cancel.addActionListener(new CancelBasic(_w.getDialogTabulations()));
        all_.add(cancel);
        _w.getDialogTabulations().setContentPane(all_);
        _w.getDialogTabulations().pack();
        _w.getDialogTabulations().setVisible(true);
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
