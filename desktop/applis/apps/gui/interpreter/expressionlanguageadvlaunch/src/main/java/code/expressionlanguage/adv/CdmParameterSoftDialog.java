package code.expressionlanguage.adv;

import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class CdmParameterSoftDialog {
    private final AbsButton val;
    private final AbsCustCheckBox check;
    public CdmParameterSoftDialog(WindowCdmEditor _w) {
        AbstractProgramInfos factories_ = _w.getFrames();
        AbsPanel all_ = factories_.getCompoFactory().newPageBox();
        AbsPanel buttons_ = factories_.getCompoFactory().newLineBox();
        StringMap<String> mes_ = MessagesIde.valGlobalParameters(_w.getFrames().currentLg());
        check = factories_.getCompoFactory().newCustCheckBox(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_GLOBAL_PARAMETERS_DIRECT_MATCH)),_w.getSoftParams().isDirectMatchKeyValue());
        all_.add(check);
        val = factories_.getCompoFactory().newPlainButton(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_GLOBAL_PARAMETERS_VALIDATE)));
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
