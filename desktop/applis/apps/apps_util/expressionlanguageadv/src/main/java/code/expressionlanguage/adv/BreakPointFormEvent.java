package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.syntax.ResultExpressionOperationNode;
import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.exec.dbg.*;
import code.expressionlanguage.options.ResultContext;
import code.gui.AbsCommonFrame;
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
        ExecFileBlock f_ = r_.getFiles().getVal(af_);
        int caret_ = tabEditor.getCenter().getCaretPosition();
        int o_ = ResultExpressionOperationNode.beginPart(caret_, af_);
        MemberCallingsBlock id_ = ResultExpressionOperationNode.keyMethodBp(caret_, af_);
        if (id_ != null) {
            BracedBlock rPar_ = BreakPointBlockList.rootOfAnnot(id_);
            if (rPar_ instanceof RootBlock) {
                WatchPointFormEvent.watchAction(r_, false,((RootBlock)rPar_).getNumberAll(),((NamedCalledFunctionBlock)id_).getName(), window.getFramePoints().getFrameWpFormContent(),window, window.getFramePoints().getCommonFrame());
                return;
            }
            MethodPointBlockPair mp_ = r_.getPair(MemberCallingsBlock.clName(id_));
            if (mp_ != null) {
                methodAction(mp_, window.getFramePoints().getFrameFormContent(), window.getFramePoints().getCommonFrame());
                return;
            }
        }
        BreakPointBlockPair bp_ = r_.getPair(f_, o_);
        if (bp_ == null) {
            return;
        }
        bpAction(bp_, window.getFramePoints().getCommonFrame(), window.getFramePoints().getFrameBpFormContent());
    }

    static void bpAction(BreakPointBlockPair _pair, AbsCommonFrame _f, FrameBpFormContent _bp) {
        _bp.setSelectedBp(_pair);
        _bp.getEnabledBp().setSelected(_pair.getValue().isEnabled());
        specific(_bp.getGuiStdStackForm(), !_pair.getValue().isEnabledChgtType(), _pair.getValue().getResultStd(), _f);
        specific(_bp.getGuiStaStackForm(), _pair.getValue().isEnabledChgtType(), _pair.getValue().getResultStatic(), _f);
        specific(_bp.getGuiInsStackForm(), _pair.getValue().isEnabledChgtType(), _pair.getValue().getResultInstance(), _f);
        _bp.getInstanceType().setEnabled(_pair.getValue().isEnabledChgtType());
        _bp.getInstanceType().setSelected(_pair.getValue().isInstanceType());
        _bp.getStaticType().setEnabled(_pair.getValue().isEnabledChgtType());
        _bp.getStaticType().setSelected(_pair.getValue().isStaticType());
        _f.setVisible(true);
        PackingWindowAfter.pack(_f);
    }

    static void methodAction(MethodPointBlockPair _mp, FrameMpForm _mePoint, AbsCommonFrame _frame) {
        _mePoint.getEdited().setText(_mp.getSgn());
        _mePoint.setSelectedMp(_mp);
        _mePoint.getEnabledMp().setSelected(_mp.getValue().isEnabled());
        _mePoint.getFrameMpFormContent().getPref().setValue(_mp.getPref().get());
        specific(_mePoint.getGuiEnterStackForm(), true, _mp.getValue().getResultEntry(), _frame);
        specific(_mePoint.getGuiExitStackForm(), true, _mp.getValue().getResultExit(), _frame);
        _mePoint.getEnterFunction().setSelected(_mp.getValue().isEntry());
        _mePoint.getExitFunction().setSelected(_mp.getValue().isExit());
        _frame.setVisible(true);
        PackingWindowAfter.pack(_frame);
    }

    static void specific(GuiStackForm _specForm, boolean _visible, BreakPointCondition _model, AbsCommonFrame _frame) {
        _specForm.getConditional().setVisible(_visible);
        _specForm.getConditional().setText(_model.getResultStr());
        _specForm.getEnabledSub().setVisible(_visible);
        _specForm.getEnabledSub().setSelected(_model.getEnabled().get());
        _specForm.getHit().setVisible(_visible);
        _specForm.getHit().setSelected(_model.getHit().get());
        _specForm.getDisabledWhenHit().setVisible(_visible);
        _specForm.getDisabledWhenHit().setSelected(_model.getDisableWhenHit().get());
        _specForm.getCount().setVisible(_visible);
        _specForm.getCount().setValue(_model.getCountModulo().get());
        _specForm.getCountSub().setVisible(_visible);
        _specForm.getCountSub().setValue(_model.getCount().get());
        _specForm.getStaScIncExc().setVisible(_visible);
        feed(_specForm.getMustBe(), _model.getInclude());
        feed(_specForm.getMustNotBe(), _model.getExclude());
        _specForm.actualiseLists(_frame);
    }

    static void feed(CustList<AbsCallContraints> _specForm, AbsCollection<AbsCallContraints> _model) {
        _specForm.clear();
        for (AbsCallContraints e: _model.elts()) {
            _specForm.add(e);
        }
    }

}
