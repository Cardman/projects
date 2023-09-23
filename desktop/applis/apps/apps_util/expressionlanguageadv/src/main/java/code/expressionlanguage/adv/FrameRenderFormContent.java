package code.expressionlanguage.adv;

import code.expressionlanguage.options.ResultContext;
import code.gui.*;

public final class FrameRenderFormContent {
    private AbsTextArea renderText;
    private RenderPointPair selectedExc;
    private AbsTextField clName;
    private AbsCustCheckBox exact;
    private AbsCustCheckBox enabledExc;
    private AbsPlainButton ok;
    private AbsPlainButton remove;
    private AbsPanel contentPane;

    public void guiBuild(AbsDebuggerGui _d) {
        enabledExc = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("enabled");
        ok = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton("ok");
        remove = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton("remove");
        clName = _d.getCommonFrame().getFrames().getCompoFactory().newTextField();
        exact = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("exact");
        renderText = _d.getCommonFrame().getFrames().getCompoFactory().newTextArea();
        AbsPanel bpForm_ = _d.getCommonFrame().getFrames().getCompoFactory().newPageBox();
        bpForm_.add(exact);
        bpForm_.add(clName);
        bpForm_.add(enabledExc);
        bpForm_.add(_d.getCommonFrame().getFrames().getCompoFactory().newAbsScrollPane(renderText));
        bpForm_.add(ok);
        bpForm_.add(remove);
        contentPane = bpForm_;
    }
    public void initForm(RenderPointPair _s) {
        setSelectedExc(_s);
        RenderPointPair exc_ = getSelectedExc();
        if (exc_ != null) {
            exact.setEnabled(false);
            exact.setSelected(exc_.getExcPointBlockPair().getEp().isExact());
            clName.setEnabled(false);
            clName.setText(exc_.getExcPointBlockPair().getEp().getClName());
            getEnabledExc().setSelected(exc_.getExcPointBlockPair().getValue().isEnabled());
            renderText.setText(exc_.getRender().getResultStr());
        } else {
            getEnabledExc().setSelected(true);
            exact.setEnabled(true);
            clName.setEnabled(true);
        }
    }

    public void refresh(ResultContext _r, AbsDebuggerGui _d, FramePoints _p) {
        GuiBaseUtil.removeActionListeners(ok);
        ok.addActionListener(new OkRenderFormEvent(_d,this, _p, _r));
        GuiBaseUtil.removeActionListeners(remove);
        remove.addActionListener(new OkRemoveRenderFormEvent(_d,this, _p));
    }

    public AbsPanel getContentPane() {
        return contentPane;
    }

    public RenderPointPair getSelectedExc() {
        return selectedExc;
    }

    public void setSelectedExc(RenderPointPair _s) {
        this.selectedExc = _s;
    }

    public AbsCustCheckBox getExact() {
        return exact;
    }

    public AbsTextField getClName() {
        return clName;
    }

    public AbsCustCheckBox getEnabledExc() {
        return enabledExc;
    }

    public AbsTextArea getRenderText() {
        return renderText;
    }

    public AbsPlainButton getOk() {
        return ok;
    }

    public AbsPlainButton getRemove() {
        return remove;
    }

}
