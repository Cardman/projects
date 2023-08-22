package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.syntax.ResultExpressionOperationNode;
import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.exec.dbg.*;
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
        int caret_ = tabEditor.getCenter().getCaretPosition();
        int o_ = ResultExpressionOperationNode.beginPart(caret_, af_);
        MemberCallingsBlock id_ = ResultExpressionOperationNode.keyMethodBp(caret_, af_);
        if (id_ != null) {
            BracedBlock rPar_ = BreakPointBlockList.rootOfAnnot(id_);
            if (rPar_ instanceof RootBlock) {
                WatchPointFormEvent.watchAction(r_,window,false,((RootBlock)rPar_).getNumberAll(),((NamedCalledFunctionBlock)id_).getName());
                return;
            }
            MethodPointBlockPair mp_ = r_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPair(MemberCallingsBlock.clName(id_));
            if (mp_ != null) {
                window.getFrameMpForm().setSelectedMp(mp_);
                window.getFrameMpForm().getEnabledMp().setSelected(mp_.getValue().isEnabled());
                specific(window.getFrameMpForm().getGuiEnterStackForm(), true, mp_.getValue().getResultEntry(), window);
                specific(window.getFrameMpForm().getGuiExitStackForm(), true, mp_.getValue().getResultExit(), window);
                window.getFrameMpForm().getEnterFunction().setSelected(mp_.getValue().isEntry());
                window.getFrameMpForm().getExitFunction().setSelected(mp_.getValue().isExit());
                window.getFrameMpForm().getCommonFrame().setVisible(true);
                PackingWindowAfter.pack(window.getFrameMpForm().getCommonFrame());
                return;
            }
        }
        BreakPointBlockPair bp_ = r_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPair(f_, o_);
        if (bp_ == null) {
            return;
        }
        window.getFrameBpForm().setSelectedBp(bp_);
        window.getFrameBpForm().getEnabledBp().setSelected(bp_.getValue().isEnabled());
        specific(window.getFrameBpForm().getGuiStdStackForm(), !bp_.getValue().isEnabledChgtType(), bp_.getValue().getResultStd(), window);
        specific(window.getFrameBpForm().getGuiStaStackForm(), bp_.getValue().isEnabledChgtType(), bp_.getValue().getResultStatic(), window);
        specific(window.getFrameBpForm().getGuiInsStackForm(), bp_.getValue().isEnabledChgtType(), bp_.getValue().getResultInstance(), window);
        window.getFrameBpForm().getInstanceType().setEnabled(bp_.getValue().isEnabledChgtType());
        window.getFrameBpForm().getInstanceType().setSelected(bp_.getValue().isInstanceType());
        window.getFrameBpForm().getStaticType().setEnabled(bp_.getValue().isEnabledChgtType());
        window.getFrameBpForm().getStaticType().setSelected(bp_.getValue().isStaticType());
        window.getFrameBpForm().getCommonFrame().setVisible(true);
        PackingWindowAfter.pack(window.getFrameBpForm().getCommonFrame());
    }

    static void specific(GuiStackForm _specForm, boolean _visible, BreakPointCondition _model, AbsDebuggerGui _win) {
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
        _specForm.actualiseLists(_win);
    }

    static void feed(CustList<AbsCallContraints> _specForm, AbsCollection<AbsCallContraints> _model) {
        _specForm.clear();
        for (AbsCallContraints e: _model.elts()) {
            _specForm.add(e);
        }
    }

}
