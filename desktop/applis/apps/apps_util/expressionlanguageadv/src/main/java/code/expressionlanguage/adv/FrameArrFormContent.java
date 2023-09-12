package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.ArrPoint;
import code.expressionlanguage.exec.dbg.ArrPointBlockPair;
import code.expressionlanguage.options.ResultContext;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringMap;

public final class FrameArrFormContent {
    private final GuiStackForm guiLengthStackForm;
    private ArrPointBlockPair selectedArr;
    private AbsTextField clName;
    private AbsCustCheckBox exact;
    private AbsCustCheckBox length;
    private AbsCustCheckBox enabledExc;
    private AbsPlainButton ok;
    private AbsPlainButton remove;
    private AbsPanel contentPane;

    public FrameArrFormContent(AbstractProgramInfos _c) {
        guiLengthStackForm = new GuiStackForm(_c);
    }

    public void guiBuild(AbsDebuggerGui _d) {
        length = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("thrown");
        enabledExc = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("enabled");
        ok = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton("ok");
        remove = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton("remove");
        clName = _d.getCommonFrame().getFrames().getCompoFactory().newTextField();
        exact = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("exact");
        AbsPanel bpForm_ = _d.getCommonFrame().getFrames().getCompoFactory().newPageBox();
        bpForm_.add(exact);
        bpForm_.add(clName);
        bpForm_.add(enabledExc);
        bpForm_.add(length);
        bpForm_.add(guiLengthStackForm.guiBuild(_d));
        bpForm_.add(ok);
        bpForm_.add(remove);
        contentPane = bpForm_;
    }
    public void initForm(ArrPointBlockPair _s, AbsCommonFrame _f, ResultContext _r) {
        setSelectedArr(_s);
        ArrPointBlockPair exc_ = getSelectedArr();
        if (exc_ != null) {
            exact.setEnabled(false);
            exact.setSelected(exc_.getEp().isExact());
            clName.setEnabled(false);
            clName.setText(exc_.getEp().getClName());
            remove.setEnabled(true);
            getEnabledExc().setSelected(exc_.getValue().isEnabled());
            BreakPointFormEvent.specific(getGuiLengthStackForm(), true, exc_.getValue().getResultLength(), _f,_r);
            getLength().setSelected(exc_.getValue().isLength());
        } else {
            getGuiLengthStackForm().getDependantPointsForm().init(_r, ArrPoint.AP);
            exact.setEnabled(true);
            clName.setEnabled(true);
            remove.setEnabled(false);
        }
    }

    public void refresh(StringMap<String> _v, ResultContext _r, AbsDebuggerGui _d, FramePoints _p) {
        GuiBaseUtil.removeActionListeners(ok);
        ok.addActionListener(new OkArrFormEvent(_d,this, _p, _r));
        GuiBaseUtil.removeActionListeners(remove);
        remove.addActionListener(new OkRemoveArrFormEvent(this, _p, _r));
        getGuiLengthStackForm().refresh(_v, "", _r, _d);
    }

    public AbsPanel getContentPane() {
        return contentPane;
    }

    public ArrPointBlockPair getSelectedArr() {
        return selectedArr;
    }

    public void setSelectedArr(ArrPointBlockPair _s) {
        this.selectedArr = _s;
    }

    public AbsCustCheckBox getExact() {
        return exact;
    }

    public AbsTextField getClName() {
        return clName;
    }

    public AbsCustCheckBox getLength() {
        return length;
    }

    public AbsCustCheckBox getEnabledExc() {
        return enabledExc;
    }

    public AbsPlainButton getOk() {
        return ok;
    }

    public AbsPlainButton getRemove() {
        return remove;
    }

    public GuiStackForm getGuiLengthStackForm() {
        return guiLengthStackForm;
    }
}
