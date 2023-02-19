package code.expressionlanguage.adv;

import code.gui.AbsPanel;
import code.gui.AbsPlainButton;
import code.gui.AbsSpinner;
import code.gui.initialize.AbstractProgramInfos;

public final class OutputDialogTab {
    private final WindowCdmEditor windowCdmEditor;
    private AbsPlainButton val;
    private AbsPlainButton cancel;
    private AbsSpinner tabulation;

    public OutputDialogTab(WindowCdmEditor _w) {
        windowCdmEditor = _w;
    }
    public void update() {
        AbstractProgramInfos factories_ = windowCdmEditor.getCommonFrame().getFrames();
        AbsPanel all_ = factories_.getCompoFactory().newPageBox();
        tabulation = factories_.getCompoFactory().newSpinner(windowCdmEditor.getTabWidth(),1,64,1);
        all_.add(tabulation);
        val = factories_.getCompoFactory().newPlainButton("OK");
        val.addActionListener(new ValidateTabulations(tabulation,windowCdmEditor));
        all_.add(val);
        cancel = factories_.getCompoFactory().newPlainButton("KO");
        cancel.addActionListener(new CancelBasic(windowCdmEditor.getDialogTabulations()));
        all_.add(cancel);
        windowCdmEditor.getDialogTabulations().setContentPane(all_);
        windowCdmEditor.getDialogTabulations().pack();
        windowCdmEditor.getDialogTabulations().setVisible(true);
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
