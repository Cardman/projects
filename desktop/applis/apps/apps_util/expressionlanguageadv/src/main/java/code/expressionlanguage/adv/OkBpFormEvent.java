package code.expressionlanguage.adv;

import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.exec.dbg.AbsPairPoint;
import code.expressionlanguage.exec.dbg.BreakPointBlockPair;
import code.expressionlanguage.exec.dbg.BreakPointCondition;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.options.ResultContextLambda;
import code.gui.events.AbsActionListener;

public final class OkBpFormEvent implements AbsActionListener {
    private final AbsDebuggerGui window;
    private final ResultContext currentResult;

    public OkBpFormEvent(AbsDebuggerGui _w, ResultContext _res) {
        this.window = _w;
        currentResult = _res;
    }

    @Override
    public void action() {
        BreakPointBlockPair bp_ = window.getFramePoints().getFrameBpFormContent().getSelectedBp();
        if (bp_ == null) {
            AbsPairPoint p_ = currentResult.tryGetPair(window.getFramePoints().getFrameBpFormContent().getFileName().getText(), window.getFramePoints().getFrameBpFormContent().getCaret().getValue());
            if (!(p_ instanceof BreakPointBlockPair)) {
                return;
            }
            bp_ = (BreakPointBlockPair) p_;
            currentResult.bpList().add(bp_);
        }
        bpAction(window, window.getFramePoints().getFrameBpFormContent(), bp_, currentResult);
        window.getFramePoints().guiContentBuildClear();
        window.getFramePoints().refreshBp(window, currentResult);
        window.getFramePoints().getCommonFrame().pack();
    }

    static void bpAction(AbsDebuggerGui _win, FrameBpFormContent _bp, BreakPointBlockPair _selected, ResultContext _res) {
        _selected.getValue().setEnabled(_bp.getEnabledBp().isSelected());
        _selected.getValue().setInstanceType(_bp.getInstanceType().isSelected());
        _selected.getValue().setStaticType(_bp.getStaticType().isSelected());
        if (_selected.getValue().isEnabledChgtType()) {
            update(_selected, _selected.getValue().getResultInstance(), _win, _bp.getGuiInsStackForm(),MethodAccessKind.INSTANCE, _res);
            update(_selected, _selected.getValue().getResultStatic(), _win, _bp.getGuiStaStackForm(),MethodAccessKind.STATIC, _res);
        } else {
            update(_selected, _selected.getValue().getResultStd(), _win, _bp.getGuiStdStackForm(),null, _res);
        }
        _bp.setSelectedBp(null);
    }

    private static void update(BreakPointBlockPair _mp, BreakPointCondition _condition, AbsDebuggerGui _window, GuiStackForm _form, MethodAccessKind _flag, ResultContext _curr) {
        String type_ = _curr.getPageEl().getAliasPrimBoolean();
        ResultContextLambda res_ = ResultContextLambda.dynamicAnalyze(_form.getConditional().getText(), ExecFileBlock.name(_mp.getBp().getFile()), _mp.getBp().getOffset(), _curr, type_, _window.getResultContextNext().generateAdv(_curr.getContext().getInterrupt()), _flag);
        OkMpFormEvent.update(_condition,_form,res_);
    }
}
