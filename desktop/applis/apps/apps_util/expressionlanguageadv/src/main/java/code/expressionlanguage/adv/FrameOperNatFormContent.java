package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.OperNatPoint;
import code.expressionlanguage.exec.dbg.OperNatPointBlockPair;
import code.expressionlanguage.options.ResultContext;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringMap;

public final class FrameOperNatFormContent {
    private final GuiStackForm guiSimpleStackForm;
    private final GuiStackForm guiCompoundStackForm;
    private OperNatPointBlockPair selectedOperNat;
    private AbsTextField symbol;
    private AbsTextField first;
    private AbsTextField second;
    private AbsCustCheckBox simple;
    private AbsCustCheckBox compound;
    private AbsCustCheckBox enabledOperNat;
    private AbsPlainButton ok;
    private AbsPlainButton remove;
    private AbsPanel contentPane;

    public FrameOperNatFormContent(AbstractProgramInfos _c) {
        guiSimpleStackForm = new GuiStackForm(_c);
        guiCompoundStackForm = new GuiStackForm(_c);
    }

    public void guiBuild(AbsDebuggerGui _d) {
        simple = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("get");
        compound = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("get");
        enabledOperNat = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("enabled");
        ok = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton("ok");
        remove = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton("remove");
        symbol = _d.getCommonFrame().getFrames().getCompoFactory().newTextField();
        first = _d.getCommonFrame().getFrames().getCompoFactory().newTextField();
        second = _d.getCommonFrame().getFrames().getCompoFactory().newTextField();
        AbsPanel bpForm_ = _d.getCommonFrame().getFrames().getCompoFactory().newPageBox();
        bpForm_.add(symbol);
        bpForm_.add(first);
        bpForm_.add(second);
        bpForm_.add(enabledOperNat);
        bpForm_.add(simple);
        bpForm_.add(compound);
        bpForm_.add(guiSimpleStackForm.guiBuild(_d));
        bpForm_.add(guiCompoundStackForm.guiBuild(_d));
        bpForm_.add(ok);
        bpForm_.add(remove);
        contentPane = bpForm_;
    }
    public void initForm(OperNatPointBlockPair _s, AbsCommonFrame _f, ResultContext _r) {
        setSelectedOperNat(_s);
        OperNatPointBlockPair exc_ = getSelectedOperNat();
        if (exc_ != null) {
            symbol.setEnabled(false);
            symbol.setText(exc_.getSymbol());
            first.setEnabled(false);
            first.setText(exc_.getOn().getFirst());
            second.setEnabled(false);
            second.setText(exc_.getOn().getSecond());
            remove.setEnabled(true);
            getEnabledOperNat().setSelected(exc_.getValue().isEnabled());
            BreakPointFormEvent.specific(getGuiSimpleStackForm(), true, exc_.getValue().getResultSimple(), _f,_r);
            BreakPointFormEvent.specific(getGuiCompoundStackForm(), exc_.getValue().isEnabledAffect(), exc_.getValue().getResultCompound(), _f,_r);
            getSimple().setSelected(exc_.getValue().isSimple());
            getCompound().setSelected(exc_.getValue().isCompound());
        } else {
            getGuiSimpleStackForm().getDependantPointsForm().init(_r, OperNatPoint.OP);
            getGuiCompoundStackForm().getDependantPointsForm().init(_r, OperNatPoint.OP);
            symbol.setEnabled(true);
            first.setEnabled(true);
            second.setEnabled(true);
            remove.setEnabled(false);
        }
    }

    public void refresh(StringMap<String> _v, ResultContext _r, AbsDebuggerGui _d, FramePoints _p) {
        GuiBaseUtil.removeActionListeners(ok);
        ok.addActionListener(new OkOperNatFormEvent(_d,this, _p, _r));
        GuiBaseUtil.removeActionListeners(remove);
        remove.addActionListener(new OkRemoveOperNatFormEvent(this, _p, _r));
        getGuiSimpleStackForm().refresh(_v, "", _r, _d);
        getGuiCompoundStackForm().refresh(_v, "", _r, _d);
    }

    public AbsPanel getContentPane() {
        return contentPane;
    }

    public OperNatPointBlockPair getSelectedOperNat() {
        return selectedOperNat;
    }

    public void setSelectedOperNat(OperNatPointBlockPair _s) {
        this.selectedOperNat = _s;
    }

    public AbsTextField getFirst() {
        return first;
    }

    public AbsTextField getSecond() {
        return second;
    }

    public AbsTextField getSymbol() {
        return symbol;
    }

    public AbsCustCheckBox getSimple() {
        return simple;
    }

    public AbsCustCheckBox getCompound() {
        return compound;
    }

    public AbsCustCheckBox getEnabledOperNat() {
        return enabledOperNat;
    }

    public AbsPlainButton getOk() {
        return ok;
    }

    public AbsPlainButton getRemove() {
        return remove;
    }

    public GuiStackForm getGuiSimpleStackForm() {
        return guiSimpleStackForm;
    }

    public GuiStackForm getGuiCompoundStackForm() {
        return guiCompoundStackForm;
    }
}
