package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.BreakPointBlockList;
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
    private final ExactMatchingTypeForm exactForm = new ExactMatchingTypeForm();
    private AbsCustCheckBox get;
    private AbsCustCheckBox enabledPar;
    private AbsButton ok;
    private AbsButton remove;
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
        exactForm.guiBuild(_d);
        AbsPanel bpForm_ = _d.getCommonFrame().getFrames().getCompoFactory().newPageBox();
        bpForm_.add(exactForm.getPanel());
        bpForm_.add(clName);
        bpForm_.add(enabledPar);
        bpForm_.add(get);
        bpForm_.add(guiGetStackForm.guiBuild(_d));
        getGuiGetStackForm().showPrefs();
        bpForm_.add(ok);
        bpForm_.add(remove);
        contentPane = bpForm_;
    }
    public void initForm(ParPointBlockPair _s, AbsCommonFrame _f, ResultContext _r) {
        setSelectedPar(_s);
        ParPointBlockPair exc_ = getSelectedPar();
        if (exc_ != null) {
            exactForm.updateValue(exc_.getPp());
        } else {
            exactForm.updateValue(null);
        }
        if (exc_ != null) {
            clName.setEnabled(false);
            clName.setText(exc_.getPp().getClName());
            remove.setEnabled(true);
            getEnabledPar().setSelected(exc_.getValue().isEnabled());
            BreakPointFormEvent.specific(getGuiGetStackForm(), exc_.getValue().getResultGet(), BreakPointBlockList.prefsPar(_r.getContext().parList()), _f,_r);
            getGet().setSelected(exc_.getValue().isGet());
            PackingWindowAfter.pack(_f);
        } else {
            getGuiGetStackForm().getDependantPointsForm().init(_r, ParPoint.PP);
            FrameMpForm.updatePref(BreakPointBlockList.prefsPar(_r.getContext().parList()),getGuiGetStackForm(),_f,_r);
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

    public ExactMatchingTypeForm getExactForm() {
        return exactForm;
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

    public AbsButton getOk() {
        return ok;
    }

    public AbsButton getRemove() {
        return remove;
    }

    public GuiStackForm getGuiGetStackForm() {
        return guiGetStackForm;
    }

}
