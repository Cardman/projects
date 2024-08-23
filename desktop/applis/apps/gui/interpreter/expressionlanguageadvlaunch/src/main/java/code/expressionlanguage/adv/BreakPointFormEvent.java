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
        TypePointBlockPair tp_ = currentResult.getPairType(f_, o_);
        if (tp_ != null) {
            window.getFramePoints().init(window, currentResult);
            window.getFramePoints().guiContentBuild(tp_,currentResult);
            tpAction(tp_, window.getFramePoints().getCommonFrame(), window.getFramePoints().getFrameTpFormContent(), currentResult);
            return;
        }
        BreakPointBlockPair bp_ = currentResult.getPair(f_, o_);
        if (bp_ == null) {
            return;
        }
        window.getFramePoints().init(window, currentResult);
        window.getFramePoints().guiContentBuild(bp_,currentResult);
        bpAction(bp_, window.getFramePoints().getCommonFrame(), window.getFramePoints().getFrameBpFormContent(), currentResult);
    }

    static void bpAction(BreakPointBlockPair _pair, AbsCommonFrame _f, FrameBpFormContent _bp, ResultContext _r) {
        _bp.setSelectedBp(_pair);
        _bp.getEnabledBp().setSelected(_pair.getValue().isEnabled());
        specific(_bp.getGuiStdStackForm(), _pair.getValue().getResultStd(), new CustList<BreakPointCondition>(), _f, _r);
        _f.setVisible(true);
        PackingWindowAfter.pack(_f, _bp.getFrames().getCompoFactory());
    }

    static void tpAction(TypePointBlockPair _pair, AbsCommonFrame _f, FrameTpFormContent _bp, ResultContext _r) {
        _bp.setSelectedTp(_pair);
        _bp.getEnabledBp().setSelected(_pair.getValue().isEnabled());
        specific(_bp.getGuiStaStackForm(), _pair.getValue().getResultStatic(), new CustList<BreakPointCondition>(), _f, _r);
        specific(_bp.getGuiInsStackForm(), _pair.getValue().getResultInstance(), new CustList<BreakPointCondition>(), _f, _r);
        _bp.getInstanceType().setSelected(_pair.getValue().isInstanceType());
        _bp.getStaticType().setSelected(_pair.getValue().isStaticType());
        _f.setVisible(true);
        PackingWindowAfter.pack(_f, _bp.getFrames().getCompoFactory());
    }

    static void methodAction(MethodPointBlockPair _mp, FrameMpForm _mePoint, AbsCommonFrame _frame, ResultContext _r) {
        _mePoint.getEdited().setText(_mp.getSgn());
        _mePoint.setSelectedMp(_mp);
        _mePoint.getEnabledMp().setSelected(_mp.getValue().isEnabled());
        specific(_mePoint.getGuiEnterStackForm(), _mp.getValue().getResultEntry(), BreakPointBlockList.prefsMeths(_r.getContext().metList(),MethodPoint.BPC_ENTRY), _frame,_r);
        specific(_mePoint.getGuiExitStackForm(), _mp.getValue().getResultExit(), BreakPointBlockList.prefsMeths(_r.getContext().metList(),MethodPoint.BPC_EXIT), _frame,_r);
        _mePoint.getEnterFunction().setSelected(_mp.getValue().isEntry());
        _mePoint.getExitFunction().setSelected(_mp.getValue().isExit());
        _frame.setVisible(true);
        PackingWindowAfter.pack(_frame, _mePoint.getFrameMpFormContent().getFrames().getCompoFactory());
    }

    static void specific(GuiStackForm _specForm, BreakPointCondition _model, CustList<BreakPointCondition> _bpcs, AbsCommonFrame _frame, ResultContext _r) {
        _specForm.getConditional().setVisible(true);
        _specForm.getConditional().setText(_model.getResultStr());
        _specForm.getLogs().setVisible(true);
        _specForm.getLogs().setText(_model.getLogsStr());
        _specForm.getWatches().setVisible(true);
        _specForm.getWatches().setText(_model.getWatchesStr());
        _specForm.getEnabledSub().setVisible(true);
        _specForm.getEnabledSub().setSelected(_model.getEnabled().get());
        _specForm.getHit().setVisible(true);
        _specForm.getHit().setSelected(_model.getHit().get());
        _specForm.getDisabledWhenHit().setVisible(true);
        _specForm.getDisabledWhenHit().setSelected(_model.getDisableWhenHit().get());
        _specForm.getDisableAgain().setVisible(true);
        _specForm.getDisableAgain().setSelected(_model.getDisableAgain().get());
        _specForm.getSuspend().setVisible(true);
        _specForm.getSuspend().setSelected(_model.getSuspend().get());
        _specForm.getStackLog().setVisible(true);
        _specForm.getStackLog().setSelected(_model.getStackLog().get());
        _specForm.getStackErrLog().setVisible(true);
        _specForm.getStackErrLog().setSelected(_model.getStackErrLog().get());
        _specForm.getStackResErrLog().setVisible(true);
        _specForm.getStackResErrLog().setSelected(_model.getStackResErrLog().get());
        _specForm.getCount().setVisible(true);
        _specForm.getCount().setValue(_model.getCountModulo().get());
        _specForm.getCountSub().setVisible(true);
        _specForm.getCountSub().setValue(_model.getCount().get());
        _specForm.getStaScIncExc().setVisible(true);
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
