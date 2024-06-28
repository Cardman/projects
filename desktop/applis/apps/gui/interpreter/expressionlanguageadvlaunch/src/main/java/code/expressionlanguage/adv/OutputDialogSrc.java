package code.expressionlanguage.adv;

import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;

public final class OutputDialogSrc implements WithFrame{
    private final AbsButton val;
    private final AbsTextField src;
    private final AbsCommonFrame frame;
    private final EnabledMenu associated;

    public OutputDialogSrc(WindowWithTreeImpl _w, AbsCommonFrame _fr, EnabledMenu _c) {
        frame = _fr;
        associated = _c;
        AbstractProgramInfos factories_ = _w.getCommonFrame().getFrames();
        AbsPanel all_ = factories_.getCompoFactory().newPageBox();
        src = factories_.getCompoFactory().newTextField(_w.getManageOptions().getEx().getSrcFolder());
        all_.add(src);
        val = factories_.getCompoFactory().newPlainButton("OK");
        val.addActionListener(new ValidateSrc(src,_w));
        all_.add(val);
        frame.setContentPane(all_);
        frame.pack();
        frame.setVisible(true);
        associated.setEnabled(false);
    }
    public void reinit(WindowWithTreeImpl _w) {
        src.setText(_w.getManageOptions().getEx().getSrcFolder());
        frame.setVisible(true);
        associated.setEnabled(false);
    }

    public AbsTextField getSrc() {
        return src;
    }

    public AbsCommonFrame getFrame() {
        return frame;
    }

    @Override
    public EnabledMenu getMenu() {
        return associated;
    }
    public AbsButton getVal() {
        return val;
    }
}
