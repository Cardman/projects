package code.expressionlanguage.adv;

import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;

public final class OutputDialogTab implements WithFrame{
    private final AbsPlainButton val;
    private final AbsSpinner tabulation;
    private final AbsCommonFrame frame;
    private final AbsMenuItem associated;

    public OutputDialogTab(WindowCdmEditor _w,AbsCommonFrame _fr, AbsMenuItem _c) {
        frame = _fr;
        associated = _c;
        AbstractProgramInfos factories_ = _w.getCommonFrame().getFrames();
        AbsPanel all_ = factories_.getCompoFactory().newPageBox();
        tabulation = factories_.getCompoFactory().newSpinner(_w.getTabWidth(),1,64,1);
        all_.add(tabulation);
        val = factories_.getCompoFactory().newPlainButton("OK");
        val.addActionListener(new ValidateTabulations(tabulation,_w));
        all_.add(val);
        frame.setContentPane(all_);
        frame.pack();
        frame.setVisible(true);
        associated.setEnabled(false);
    }
    public void reinit(WindowCdmEditor _w) {
        tabulation.setValue(_w.getTabWidth());
        frame.setVisible(true);
        associated.setEnabled(false);
    }

    public AbsSpinner getTabulation() {
        return tabulation;
    }

    public AbsCommonFrame getFrame() {
        return frame;
    }

    @Override
    public AbsMenuItem getMenu() {
        return associated;
    }
    public AbsPlainButton getVal() {
        return val;
    }
}
