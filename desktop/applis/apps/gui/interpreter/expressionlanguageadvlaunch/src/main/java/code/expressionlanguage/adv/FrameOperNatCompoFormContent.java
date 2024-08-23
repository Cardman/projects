package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.BreakPointCondition;
import code.expressionlanguage.exec.dbg.OperNatPointBlockPair;
import code.expressionlanguage.options.ResultContext;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class FrameOperNatCompoFormContent {
    private final GuiStackForm guiSimpleStackForm;
    private final GuiStackForm guiCompoundStackForm;
    private OperNatPointBlockPair selectedOperNat;
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
    private final AbstractProgramInfos frames;

    public FrameOperNatCompoFormContent(AbstractProgramInfos _c) {
        frames = _c;
        guiSimpleStackForm = new GuiStackForm(_c);
        guiCompoundStackForm = new GuiStackForm(_c);
    }

    public void guiBuild(AbsDebuggerGui _d) {
        StringMap<String> mes_ = MessagesIde.valPointsKind(_d.getFrames().currentLg());
        simple = _d.getFrames().getCompoFactory().newCustCheckBox(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_SIMPLE)));
        compound = _d.getFrames().getCompoFactory().newCustCheckBox(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_COMPOUND)));
        enabledOperNat = _d.getFrames().getCompoFactory().newCustCheckBox(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_ENABLED)));
        ok = _d.getFrames().getCompoFactory().newPlainButton(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_VALIDATE)));
        remove = _d.getFrames().getCompoFactory().newPlainButton(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_REMOVE)));
        symbol = _d.getFrames().getCompoFactory().newTextField();
        first = _d.getFrames().getCompoFactory().newTextField();
        second = _d.getFrames().getCompoFactory().newTextField();
        AbsPanel bpForm_ = _d.getFrames().getCompoFactory().newPageBox();
        bpForm_.add(symbol);
        bpForm_.add(first);
        bpForm_.add(second);
        bpForm_.add(enabledOperNat);
        bpForm_.add(simple);
        bpForm_.add(compound);
        tabs = _d.getFrames().getCompoFactory().newAbsTabbedPane();
        putStForm(tabs, StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_SIMPLE)), guiSimpleStackForm.guiBuild(_d));
        putStForm(tabs, StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_COMPOUND)), guiCompoundStackForm.guiBuild(_d));
        bpForm_.add(tabs);
        bpForm_.add(ok);
        bpForm_.add(remove);
        contentPane = bpForm_;
    }

    private void putStForm(AbsTabbedPane _tab, String _title, AbsScrollPane _compo) {
        _tab.addIntTab(_title, _compo);
    }

    public void initForm(OperNatPointBlockPair _s, AbsCommonFrame _f, ResultContext _r) {
        setSelectedOperNat(_s);
        OperNatPointBlockPair exc_ = getSelectedOperNat();
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
            PackingWindowAfter.pack(_f, frames.getCompoFactory());
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
        getGuiSimpleStackForm().refresh(_v, AbsEditorTabList.EMPTY_STRING, _r, _d);
        getGuiCompoundStackForm().refresh(_v, AbsEditorTabList.EMPTY_STRING, _r, _d);
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
