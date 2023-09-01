package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.*;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.options.ResultContextLambda;
import code.gui.events.AbsActionListener;

public final class OkMpFormEvent implements AbsActionListener {
    private final AbsDebuggerGui window;

    public OkMpFormEvent(AbsDebuggerGui _w) {
        this.window = _w;
    }

    @Override
    public void action() {
        MethodPointBlockPair mp_ = window.getFramePoints().getFrameFormContent().getSelectedMp();
        if (mp_ == null) {
            AbsPairPoint p_ = window.getCurrentResult().tryGetPair(window.getFramePoints().getFrameFormContent().getFileName().getText(), window.getFramePoints().getFrameFormContent().getCaret().getValue());
            if (!(p_ instanceof MethodPointBlockPair)) {
                return;
            }
            mp_ = (MethodPointBlockPair) p_;
            window.getCurrentResult().getContext().metList().add(mp_);
        }
        mp_.getValue().setEnabled(window.getFramePoints().getFrameFormContent().getEnabledMp().isSelected());
        mp_.getPref().set(window.getFramePoints().getFrameFormContent().getFrameMpFormContent().getPref().getValue());
        mp_.getValue().setEntry(window.getFramePoints().getFrameFormContent().getEnterFunction().isSelected());
        mp_.getValue().setExit(window.getFramePoints().getFrameFormContent().getExitFunction().isSelected());
        update(mp_, mp_.getValue().getResultEntry(), window, window.getFramePoints().getFrameFormContent().getGuiEnterStackForm());
        update(mp_, mp_.getValue().getResultExit(), window, window.getFramePoints().getFrameFormContent().getGuiExitStackForm());
        window.getFramePoints().getFrameFormContent().setSelectedMp(null);
        window.getFramePoints().guiContentBuildClear();
        window.getFramePoints().refreshMethod(window);
        window.getFramePoints().getCommonFrame().pack();
    }
    private static void update(MethodPointBlockPair _mp,BreakPointCondition _condition,AbsDebuggerGui _window, GuiStackForm _form) {
        ResultContext curr_ = _window.getCurrentResult();
        String type_ = curr_.getPageEl().getAliasPrimBoolean();
        ResultContextLambda res_ = ResultContextLambda.dynamicAnalyze(_form.getConditional().getText(), _mp, curr_, type_, _window.getResultContextNext().generateAdv(curr_.getContext().getInterrupt()));
        update(_condition, _form, res_);
    }

    static void update(BreakPointCondition _condition, GuiStackForm _form, ResultContextLambda _res) {
        _condition.result(_res, _form.getConditional().getText());
        _condition.getCountModulo().set(_form.getCount().getValue());
        _condition.getCount().set(_form.getCountSub().getValue());
        ExecFileBlockTraceIndex.setAll(_condition.getExclude(),_form.getMustNotBe());
        ExecFileBlockTraceIndex.setAll(_condition.getInclude(),_form.getMustBe());
        _condition.getEnabled().set(_form.getEnabledSub().isSelected());
        _condition.getHit().set(_form.getHit().isSelected());
        _condition.getDisableWhenHit().set(_form.getDisabledWhenHit().isSelected());

    }
}
