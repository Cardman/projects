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

    public OkBpFormEvent(AbsDebuggerGui _w) {
        this.window = _w;
    }

    @Override
    public void action() {
        BreakPointBlockPair bp_ = window.getFramePoints().getFrameBpFormContent().getSelectedBp();
        if (bp_ == null) {
            AbsPairPoint p_ = window.getCurrentResult().tryGetPair(window.getFramePoints().getFrameBpFormContent().getFileName().getText(), window.getFramePoints().getFrameBpFormContent().getCaret().getValue());
            if (!(p_ instanceof BreakPointBlockPair)) {
                return;
            }
            bp_ = (BreakPointBlockPair) p_;
            window.getCurrentResult().bpList().add(bp_);
        }
        bpAction(window, window.getFramePoints().getFrameBpFormContent(), bp_);
        window.getFramePoints().guiContentBuildClear();
        window.getFramePoints().refreshBp(window);
        window.getFramePoints().getCommonFrame().pack();
    }

    static void bpAction(AbsDebuggerGui _win, FrameBpFormContent _bp, BreakPointBlockPair _selected) {
        _selected.getValue().setEnabled(_bp.getEnabledBp().isSelected());
        _selected.getValue().setInstanceType(_bp.getInstanceType().isSelected());
        _selected.getValue().setStaticType(_bp.getStaticType().isSelected());
        if (_selected.getValue().isEnabledChgtType()) {
            update(_selected, _selected.getValue().getResultInstance(), _win, _bp.getGuiInsStackForm(),MethodAccessKind.INSTANCE);
            update(_selected, _selected.getValue().getResultStatic(), _win, _bp.getGuiStaStackForm(),MethodAccessKind.STATIC);
        } else {
            update(_selected, _selected.getValue().getResultStd(), _win, _bp.getGuiStdStackForm(),null);
        }
        _bp.setSelectedBp(null);
    }

    private static void update(BreakPointBlockPair _mp, BreakPointCondition _condition, AbsDebuggerGui _window, GuiStackForm _form, MethodAccessKind _flag) {
        ResultContext curr_ = _window.getCurrentResult();
        String type_ = curr_.getPageEl().getAliasPrimBoolean();
        ResultContextLambda res_ = ResultContextLambda.dynamicAnalyze(_form.getConditional().getText(), ExecFileBlock.name(_mp.getBp().getFile()), _mp.getBp().getOffset(), curr_, type_, _window.getResultContextNext().generateAdv(curr_.getContext().getInterrupt()), _flag);
        OkMpFormEvent.update(_condition,_form,res_);
    }
}
