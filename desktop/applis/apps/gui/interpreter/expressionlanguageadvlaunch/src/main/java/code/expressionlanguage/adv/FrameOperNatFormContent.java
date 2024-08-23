package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.BreakPointCondition;
import code.expressionlanguage.exec.dbg.OperNatPoint;
import code.expressionlanguage.exec.dbg.OperNatPointBlockPair;
import code.expressionlanguage.options.ResultContext;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class FrameOperNatFormContent {
    private final GuiStackForm guiSimpleStackForm;
    private OperNatPointBlockPair selectedOperNat;
    private AbsTextField symbol;
    private AbsTextField first;
    private AbsTextField second;
    private AbsCustCheckBox simple;
    private AbsCustCheckBox enabledOperNat;
    private AbsButton ok;
    private AbsButton remove;
    private AbsPanel contentPane;
    private AbsTabbedPane tabs;
    private final AbstractProgramInfos frames;

    public FrameOperNatFormContent(AbstractProgramInfos _c) {
        frames = _c;
        guiSimpleStackForm = new GuiStackForm(_c);
    }

    public void guiBuild(AbsDebuggerGui _d) {
        StringMap<String> mes_ = MessagesIde.valPointsKind(_d.getFrames().currentLg());
        simple = _d.getFrames().getCompoFactory().newCustCheckBox(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_SIMPLE)));
        enabledOperNat = _d.getFrames().getCompoFactory().newCustCheckBox(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_ENABLED)));
        ok = _d.getFrames().getCompoFactory().newPlainButton(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_VALIDATE)));
        remove = _d.getFrames().getCompoFactory().newPlainButton(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_REMOVE)));
        symbol = _d.getFrames().getCompoFactory().newTextField();
        first = _d.getFrames().getCompoFactory().newTextField();
        second = _d.getFrames().getCompoFactory().newTextField();
        AbsPanel onForm_ = _d.getFrames().getCompoFactory().newPageBox();
        onForm_.add(symbol);
        onForm_.add(first);
        onForm_.add(second);
        onForm_.add(enabledOperNat);
        onForm_.add(simple);
        tabs = _d.getFrames().getCompoFactory().newAbsTabbedPane();
        putStForm(tabs, StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_KIND_SIMPLE)), guiSimpleStackForm.guiBuild(_d));
        onForm_.add(tabs);
        onForm_.add(ok);
        onForm_.add(remove);
        contentPane = onForm_;
    }

    private void putStForm(AbsTabbedPane _tab, String _title, AbsScrollPane _compo) {
        _tab.addIntTab(_title, _compo);
    }

    public void initForm(OperNatPointBlockPair _s, AbsCommonFrame _f, ResultContext _r) {
        setSelectedOperNat(_s);
        OperNatPointBlockPair exc_ = getSelectedOperNat();
        tabs.removeAll();
        if (exc_ != null) {
            operNat(exc_);
            getEnabledOperNat().setSelected(exc_.getValue().isEnabled());
            BreakPointFormEvent.specific(getGuiSimpleStackForm(), exc_.getValue().getResultSimple(),new CustList<BreakPointCondition>(),  _f,_r);
            getSimple().setSelected(exc_.getValue().isSimple());
            PackingWindowAfter.pack(_f, frames.getCompoFactory());
        } else {
            getGuiSimpleStackForm().getDependantPointsForm().init(_r, OperNatPoint.OP);
            symbol.setEnabled(true);
            first.setEnabled(true);
            second.setEnabled(true);
            remove.setEnabled(false);
        }
    }

    private void operNat(OperNatPointBlockPair _exc) {
        symbol.setEnabled(false);
        symbol.setText(_exc.getSymbol());
        first.setEnabled(false);
        first.setText(_exc.getOn().getFirst());
        second.setEnabled(false);
        second.setText(_exc.getOn().getSecond());
        remove.setEnabled(true);
    }

    public void refresh(StringMap<String> _v, ResultContext _r, AbsDebuggerGui _d, FramePoints _p) {
        GuiBaseUtil.removeActionListeners(ok);
        ok.addActionListener(new OkOperNatFormEvent(_d,this, _p, _r));
        GuiBaseUtil.removeActionListeners(remove);
        remove.addActionListener(new OkRemoveOperNatFormEvent(this, _p, _r));
        getGuiSimpleStackForm().refresh(_v, AbsEditorTabList.EMPTY_STRING, _r, _d);
    }

    public AbsTextField getFirst() {
        return first;
    }

    public AbsTextField getSecond() {
        return second;
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

    public AbsTextField getSymbol() {
        return symbol;
    }

    public AbsCustCheckBox getSimple() {
        return simple;
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

}
