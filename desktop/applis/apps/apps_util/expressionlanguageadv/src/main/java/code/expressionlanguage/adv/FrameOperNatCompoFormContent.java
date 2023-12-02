package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.BreakPointCondition;
import code.expressionlanguage.exec.dbg.CompoOperNatPointBlockPair;
import code.expressionlanguage.options.ResultContext;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.util.CustList;
import code.util.StringMap;

public final class FrameOperNatCompoFormContent {
    private final GuiStackForm guiSimpleStackForm;
    private final GuiStackForm guiCompoundStackForm;
    private CompoOperNatPointBlockPair selectedOperNat;
    private AbsTextField symbol;
    private AbsTextField first;
    private AbsTextField second;
    private AbsCustCheckBox simple;
    private AbsCustCheckBox compound;
    private AbsCustCheckBox enabledOperNat;
    private AbsButton ok;
    private AbsButton remove;
    private AbsPanel contentPane;
    private AbsTabbedPane tabs;

    public FrameOperNatCompoFormContent(AbstractProgramInfos _c) {
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
        tabs = _d.getCommonFrame().getFrames().getCompoFactory().newAbsTabbedPane();
        putStForm(tabs, "simple", guiSimpleStackForm.guiBuild(_d));
        putStForm(tabs, "compound", guiCompoundStackForm.guiBuild(_d));
        bpForm_.add(tabs);
        bpForm_.add(ok);
        bpForm_.add(remove);
        contentPane = bpForm_;
    }

    private void putStForm(AbsTabbedPane _tab, String _title, AbsScrollPane _compo) {
        _tab.addIntTab(_title, _compo);
    }

    public void initForm(CompoOperNatPointBlockPair _s, AbsCommonFrame _f, ResultContext _r) {
        setSelectedOperNat(_s);
        CompoOperNatPointBlockPair exc_ = getSelectedOperNat();
        tabs.removeAll();
        if (exc_ != null) {
            symbol.setEnabled(false);
            symbol.setText(exc_.getSymbol());
            first.setEnabled(false);
            first.setText(exc_.getOn().getFirst());
            second.setEnabled(false);
            second.setText(exc_.getOn().getSecond());
            remove.setEnabled(true);
            getEnabledOperNat().setSelected(exc_.getValue().isEnabled());
            BreakPointFormEvent.specific(getGuiSimpleStackForm(), exc_.getValue().getResultSimple(),new CustList<BreakPointCondition>(),  _f,_r);
            BreakPointFormEvent.specific(getGuiCompoundStackForm(), exc_.getValue().getResultCompound(),new CustList<BreakPointCondition>(),  _f,_r);
            getSimple().setSelected(exc_.getValue().isSimple());
            getCompound().setSelected(exc_.getValue().isCompound());
            PackingWindowAfter.pack(_f);
        } else {
            getGuiSimpleStackForm().getDependantPointsForm().init(_r, FramePointsTree.SORT_CP);
            getGuiCompoundStackForm().getDependantPointsForm().init(_r, FramePointsTree.SORT_CP);
            symbol.setEnabled(true);
            first.setEnabled(true);
            second.setEnabled(true);
            remove.setEnabled(false);
        }
    }

    public void refresh(StringMap<String> _v, ResultContext _r, AbsDebuggerGui _d, FramePoints _p) {
        GuiBaseUtil.removeActionListeners(ok);
        ok.addActionListener(new OkOperNatCompoFormEvent(_d,this, _p, _r));
        GuiBaseUtil.removeActionListeners(remove);
        remove.addActionListener(new OkRemoveOperNatCompoFormEvent(this, _p, _r));
        getGuiSimpleStackForm().refresh(_v, "", _r, _d);
        getGuiCompoundStackForm().refresh(_v, "", _r, _d);
    }

    public AbsPanel getContentPane() {
        return contentPane;
    }

    public CompoOperNatPointBlockPair getSelectedOperNat() {
        return selectedOperNat;
    }

    public void setSelectedOperNat(CompoOperNatPointBlockPair _s) {
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

    public AbsButton getOk() {
        return ok;
    }

    public AbsButton getRemove() {
        return remove;
    }

    public GuiStackForm getGuiSimpleStackForm() {
        return guiSimpleStackForm;
    }

    public GuiStackForm getGuiCompoundStackForm() {
        return guiCompoundStackForm;
    }
}
