package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.syntax.ResultExpressionOperationNode;
import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.exec.dbg.AbsCallContraints;
import code.expressionlanguage.exec.dbg.AbsCollection;
import code.expressionlanguage.exec.dbg.BreakPointBlockPair;
import code.expressionlanguage.exec.dbg.BreakPointCondition;
import code.expressionlanguage.options.ResultContext;
import code.gui.PackingWindowAfter;
import code.gui.events.AbsActionListener;
import code.util.CustList;

public final class BreakPointFormEvent implements AbsActionListener {
    private final AbsDebuggerGui window;
    private final ReadOnlyTabEditor tabEditor;

    public BreakPointFormEvent(AbsDebuggerGui _dbg,ReadOnlyTabEditor _t) {
        window = _dbg;
        this.tabEditor = _t;
    }

    @Override
    public void action() {
        ResultContext r_ = tabEditor.getDebuggerGui().getCurrentResult();
        FileBlock af_ = r_.getPageEl().getPreviousFilesBodies().getVal(tabEditor.getFullPath());
        ExecFileBlock f_ = r_.getForwards().dbg().getFiles().getVal(af_);
        int o_ = ResultExpressionOperationNode.beginPart(tabEditor.getCenter().getCaretPosition(), r_.getPageEl().getPreviousFilesBodies().getVal(tabEditor.getFullPath()));
        BreakPointBlockPair bp_ = r_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPair(f_, o_);
        if (bp_ == null) {
            return;
        }
        window.getFrameBpForm().setSelectedPb(bp_);
        window.getFrameBpForm().getEnabledBp().setSelected(bp_.getValue().isEnabled());
        specific(window.getFrameBpForm().getGuiStdStackForm(), !bp_.getValue().isEnabledChgtType(), bp_.getValue().getResultStd());
        specific(window.getFrameBpForm().getGuiStaStackForm(), bp_.getValue().isEnabledChgtType(), bp_.getValue().getResultStatic());
        specific(window.getFrameBpForm().getGuiInsStackForm(), bp_.getValue().isEnabledChgtType(), bp_.getValue().getResultInstance());
        window.getFrameBpForm().getInstanceType().setEnabled(bp_.getValue().isEnabledChgtType());
        window.getFrameBpForm().getInstanceType().setSelected(bp_.getValue().isInstanceType());
        window.getFrameBpForm().getStaticType().setEnabled(bp_.getValue().isEnabledChgtType());
        window.getFrameBpForm().getStaticType().setSelected(bp_.getValue().isStaticType());
        window.getFrameBpForm().getCommonFrame().setVisible(true);
        PackingWindowAfter.pack(window.getFrameBpForm().getCommonFrame());
    }

    private void specific(GuiStackForm _specForm, boolean _visible, BreakPointCondition _model) {
        _specForm.getConditional().setVisible(_visible);
        _specForm.getConditional().setText(_model.getResultStr());
        _specForm.getEnabledSub().setVisible(_visible);
        _specForm.getEnabledSub().setSelected(_model.getEnabled().get());
        _specForm.getDisabledWhenHit().setVisible(_visible);
        _specForm.getDisabledWhenHit().setSelected(_model.getDisableWhenHit().get());
        _specForm.getCount().setVisible(_visible);
        _specForm.getCount().setValue(_model.getCountModulo());
        _specForm.getStaScIncExc().setVisible(_visible);
        feed(_specForm.getMustBe(), _model.getInclude());
        feed(_specForm.getMustNotBe(), _model.getExclude());
        _specForm.actualiseLists(window);
    }

    static void feed(CustList<AbsCallContraints> _specForm, AbsCollection<AbsCallContraints> _model) {
        _specForm.clear();
        for (AbsCallContraints e: _model.elts()) {
            _specForm.add(e);
        }
    }

}
