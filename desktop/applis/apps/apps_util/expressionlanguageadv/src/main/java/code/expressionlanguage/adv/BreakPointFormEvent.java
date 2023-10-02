package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.syntax.ResultExpressionOperationNode;
import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.exec.dbg.*;
import code.expressionlanguage.options.ResultContext;
import code.gui.AbsCommonFrame;
import code.gui.GuiBaseUtil;
import code.gui.PackingWindowAfter;
import code.gui.events.AbsActionListener;
import code.util.CustList;
import code.util.StringList;

public final class BreakPointFormEvent implements AbsActionListener {
    private final AbsDebuggerGui window;
    private final ReadOnlyTabEditor tabEditor;
    private final ResultContext currentResult;

    public BreakPointFormEvent(AbsDebuggerGui _dbg, ReadOnlyTabEditor _t, ResultContext _r) {
        window = _dbg;
        this.tabEditor = _t;
        currentResult = _r;
    }

    @Override
    public void action() {
        FileBlock af_ = currentResult.getPageEl().getPreviousFilesBodies().getVal(tabEditor.getFullPath());
        ExecFileBlock f_ = currentResult.getFiles().getVal(af_);
        int caret_ = tabEditor.getCenter().getCaretPosition();
        int o_ = ResultExpressionOperationNode.beginPart(caret_, af_);
        MemberCallingsBlock id_ = ResultExpressionOperationNode.keyMethodBp(caret_, af_);
        if (id_ != null) {
            BracedBlock rPar_ = BreakPointBlockList.rootOfAnnot(id_);
            if (rPar_ instanceof RootBlock) {
                WatchPointFormEvent.watchAction(currentResult, false,((RootBlock)rPar_).getNumberAll(),((NamedCalledFunctionBlock)id_).getName(), window.getFramePoints().getFrameWpFormContent(),window, window.getFramePoints().getCommonFrame());
                return;
            }
            MethodPointBlockPair mp_ = currentResult.getPair(MemberCallingsBlock.clName(id_));
            if (mp_ != null) {
                methodAction(mp_, window.getFramePoints().getFrameFormContent(), window.getFramePoints().getCommonFrame(),currentResult);
                return;
            }
        }
        BreakPointBlockPair bp_ = currentResult.getPair(f_, o_);
        if (bp_ == null) {
            return;
        }
        bpAction(bp_, window.getFramePoints().getCommonFrame(), window.getFramePoints().getFrameBpFormContent(), currentResult);
    }

    static void bpAction(BreakPointBlockPair _pair, AbsCommonFrame _f, FrameBpFormContent _bp, ResultContext _r) {
        _bp.setSelectedBp(_pair);
        _bp.getEnabledBp().setSelected(_pair.getValue().isEnabled());
        specific(_bp.getGuiStdStackForm(), !_pair.getValue().isEnabledChgtType(), _pair.getValue().getResultStd(), new CustList<BreakPointCondition>(), _f, _r);
        specific(_bp.getGuiStaStackForm(), _pair.getValue().isEnabledChgtType(), _pair.getValue().getResultStatic(), new CustList<BreakPointCondition>(), _f, _r);
        specific(_bp.getGuiInsStackForm(), _pair.getValue().isEnabledChgtType(), _pair.getValue().getResultInstance(), new CustList<BreakPointCondition>(), _f, _r);
        _bp.getInstanceType().setEnabled(_pair.getValue().isEnabledChgtType());
        _bp.getInstanceType().setSelected(_pair.getValue().isInstanceType());
        _bp.getStaticType().setEnabled(_pair.getValue().isEnabledChgtType());
        _bp.getStaticType().setSelected(_pair.getValue().isStaticType());
        _f.setVisible(true);
        PackingWindowAfter.pack(_f);
    }

    static void methodAction(MethodPointBlockPair _mp, FrameMpForm _mePoint, AbsCommonFrame _frame, ResultContext _r) {
        _mePoint.getEdited().setText(_mp.getSgn());
        _mePoint.setSelectedMp(_mp);
        _mePoint.getEnabledMp().setSelected(_mp.getValue().isEnabled());
        specific(_mePoint.getGuiEnterStackForm(), true, _mp.getValue().getResultEntry(), BreakPointBlockList.prefsMeths(_r.getContext().metList(),MethodPoint.BPC_ENTRY), _frame,_r);
        specific(_mePoint.getGuiExitStackForm(), true, _mp.getValue().getResultExit(), BreakPointBlockList.prefsMeths(_r.getContext().metList(),MethodPoint.BPC_EXIT), _frame,_r);
        _mePoint.getEnterFunction().setSelected(_mp.getValue().isEntry());
        _mePoint.getExitFunction().setSelected(_mp.getValue().isExit());
        _frame.setVisible(true);
        PackingWindowAfter.pack(_frame);
    }

    static void specific(GuiStackForm _specForm, boolean _visible, BreakPointCondition _model, CustList<BreakPointCondition> _bpcs, AbsCommonFrame _frame, ResultContext _r) {
        _specForm.getConditional().setVisible(_visible);
        _specForm.getConditional().setText(_model.getResultStr());
        _specForm.getLogs().setVisible(_visible);
        _specForm.getLogs().setText(_model.getLogsStr());
        _specForm.getWatches().setVisible(_visible);
        _specForm.getWatches().setText(_model.getWatchesStr());
        _specForm.getEnabledSub().setVisible(_visible);
        _specForm.getEnabledSub().setSelected(_model.getEnabled().get());
        _specForm.getHit().setVisible(_visible);
        _specForm.getHit().setSelected(_model.getHit().get());
        _specForm.getDisabledWhenHit().setVisible(_visible);
        _specForm.getDisabledWhenHit().setSelected(_model.getDisableWhenHit().get());
        _specForm.getDisableAgain().setVisible(_visible);
        _specForm.getDisableAgain().setSelected(_model.getDisableAgain().get());
        _specForm.getSuspend().setVisible(_visible);
        _specForm.getSuspend().setSelected(_model.getSuspend().get());
        _specForm.getStackLog().setVisible(_visible);
        _specForm.getStackLog().setSelected(_model.getStackLog().get());
        _specForm.getStackErrLog().setVisible(_visible);
        _specForm.getStackErrLog().setSelected(_model.getStackErrLog().get());
        _specForm.getStackResErrLog().setVisible(_visible);
        _specForm.getStackResErrLog().setSelected(_model.getStackResErrLog().get());
        _specForm.getCount().setVisible(_visible);
        _specForm.getCount().setValue(_model.getCountModulo().get());
        _specForm.getCountSub().setVisible(_visible);
        _specForm.getCountSub().setValue(_model.getCount().get());
        _specForm.getStaScIncExc().setVisible(_visible);
        feed(_specForm.getMustBe(), _model.getInclude());
        feed(_specForm.getMustNotBe(), _model.getExclude());
        _specForm.actualiseLists(_frame);
        _specForm.getDependantPointsForm().init(_r,_model);
        _specForm.getPref().setValue(_model.getPref().get());
        GuiBaseUtil.initStringMapInt(_frame,_specForm.getPrefs(),_model.mapPrefs(),new StringList(_r.getContext().getClasses().getClassesBodies().getKeys()),new StrictTypeFromFilter(_r));
        GuiStackForm.initPrefs(_bpcs, _specForm.getPrefs());
    }

    static void feed(CustList<AbsCallContraints> _specForm, AbsCollection<AbsCallContraints> _model) {
        _specForm.clear();
        for (AbsCallContraints e: _model.elts()) {
            _specForm.add(e);
        }
    }

}
