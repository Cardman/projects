package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.ParPoint;
import code.expressionlanguage.exec.dbg.ParPointBlockPair;
import code.expressionlanguage.options.ResultContext;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringMap;

public final class FrameParFormContent {
    private final GuiStackForm guiGetStackForm;
    private ParPointBlockPair selectedPar;
    private AbsTextField clName;
    private AbsCustCheckBox exact;
    private AbsCustCheckBox get;
    private AbsCustCheckBox enabledPar;
    private AbsPlainButton ok;
    private AbsPlainButton remove;
    private AbsPanel contentPane;

    public FrameParFormContent(AbstractProgramInfos _c) {
        guiGetStackForm = new GuiStackForm(_c);
    }

    public void guiBuild(AbsDebuggerGui _d) {
        get = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("get");
        enabledPar = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("enabled");
        ok = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton("ok");
        remove = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton("remove");
        clName = _d.getCommonFrame().getFrames().getCompoFactory().newTextField();
        exact = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("exact");
        AbsPanel bpForm_ = _d.getCommonFrame().getFrames().getCompoFactory().newPageBox();
        bpForm_.add(exact);
        bpForm_.add(clName);
        bpForm_.add(enabledPar);
        bpForm_.add(get);
        bpForm_.add(guiGetStackForm.guiBuild(_d));
        bpForm_.add(ok);
        bpForm_.add(remove);
        contentPane = bpForm_;
    }
    public void initForm(ParPointBlockPair _s, AbsCommonFrame _f, ResultContext _r) {
        setSelectedPar(_s);
        ParPointBlockPair exc_ = getSelectedPar();
        if (exc_ != null) {
            exact.setEnabled(false);
            exact.setSelected(exc_.getPp().isExact());
            clName.setEnabled(false);
            clName.setText(exc_.getPp().getClName());
            remove.setEnabled(true);
            getEnabledPar().setSelected(exc_.getValue().isEnabled());
            BreakPointFormEvent.specific(getGuiGetStackForm(), true, exc_.getValue().getResultGet(), _f,_r);
            getGet().setSelected(exc_.getValue().isGet());
        } else {
            getGuiGetStackForm().getDependantPointsForm().init(_r, ParPoint.PP);
            exact.setEnabled(true);
            clName.setEnabled(true);
            remove.setEnabled(false);
        }
    }

    public void refresh(StringMap<String> _v, ResultContext _r, AbsDebuggerGui _d, FramePoints _p) {
        GuiBaseUtil.removeActionListeners(ok);
        ok.addActionListener(new OkParFormEvent(_d,this, _p, _r));
        GuiBaseUtil.removeActionListeners(remove);
        remove.addActionListener(new OkRemoveParFormEvent(this, _p, _r));
        getGuiGetStackForm().refresh(_v, "", _r, _d);
    }

    public AbsPanel getContentPane() {
        return contentPane;
    }

    public ParPointBlockPair getSelectedPar() {
        return selectedPar;
    }

    public void setSelectedPar(ParPointBlockPair _s) {
        this.selectedPar = _s;
    }

    public AbsCustCheckBox getExact() {
        return exact;
    }

    public AbsTextField getClName() {
        return clName;
    }

    public AbsCustCheckBox getGet() {
        return get;
    }

    public AbsCustCheckBox getEnabledPar() {
        return enabledPar;
    }

    public AbsPlainButton getOk() {
        return ok;
    }

    public AbsPlainButton getRemove() {
        return remove;
    }

    public GuiStackForm getGuiGetStackForm() {
        return guiGetStackForm;
    }

}
