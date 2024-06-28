package code.expressionlanguage.adv;

import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;

public final class CdmParameterSoftDialog {
    private final AbsButton val;
    private final AbsCustCheckBox check;
    public CdmParameterSoftDialog(WindowCdmEditor _w) {
        AbstractProgramInfos factories_ = _w.getCommonFrame().getFrames();
        AbsPanel all_ = factories_.getCompoFactory().newPageBox();
        AbsPanel buttons_ = factories_.getCompoFactory().newLineBox();
        check = factories_.getCompoFactory().newCustCheckBox("direct match",_w.getSoftParams().isDirectMatchKeyValue());
        all_.add(check);
        val = factories_.getCompoFactory().newPlainButton("OK");
        val.addActionListener(new ChangeSoftParamsEvent(this, _w));
        buttons_.add(val);
        all_.add(buttons_);
        _w.getDialogSoft().setContentPane(all_);
        _w.getDialogSoft().pack();
        _w.getDialogSoft().setVisible(true);
    }

    public AbsButton getVal() {
        return val;
    }

    public AbsCustCheckBox getCheck() {
        return check;
    }
}
