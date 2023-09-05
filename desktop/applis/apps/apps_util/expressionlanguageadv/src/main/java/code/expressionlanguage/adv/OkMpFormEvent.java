package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.*;
import code.expressionlanguage.options.ResultContext;
import code.gui.events.AbsActionListener;

public final class OkMpFormEvent implements AbsActionListener {
    private final AbsDebuggerGui window;
    private final ResultContext resultContext;

    public OkMpFormEvent(AbsDebuggerGui _w, ResultContext _r) {
        this.window = _w;
        this.resultContext = _r;
    }

    @Override
    public void action() {
        MethodPointBlockPair mp_ = window.getFramePoints().getFrameFormContent().getSelectedMp();
        if (mp_ == null) {
            AbsPairPoint p_ = resultContext.tryGetPair(window.getFramePoints().getFrameFormContent().getFileName().getText(), window.getFramePoints().getFrameFormContent().getCaret().getValue());
            if (!(p_ instanceof MethodPointBlockPair)) {
                return;
            }
            mp_ = (MethodPointBlockPair) p_;
            resultContext.getContext().metList().add(mp_);
        }
        mp_.getValue().setEnabled(window.getFramePoints().getFrameFormContent().getEnabledMp().isSelected());
        mp_.getValue().setEntry(window.getFramePoints().getFrameFormContent().getEnterFunction().isSelected());
        mp_.getValue().setExit(window.getFramePoints().getFrameFormContent().getExitFunction().isSelected());
        update(mp_, mp_.getValue().getResultEntry(), window, window.getFramePoints().getFrameFormContent().getGuiEnterStackForm(), resultContext);
        update(mp_, mp_.getValue().getResultExit(), window, window.getFramePoints().getFrameFormContent().getGuiExitStackForm(), resultContext);
        window.getFramePoints().getFrameFormContent().setSelectedMp(null);
        window.getFramePoints().guiContentBuildClear();
        window.getFramePoints().refreshMethod(window, resultContext);
        window.getFramePoints().getCommonFrame().pack();
        ToggleBreakPointEvent.afterToggle(resultContext,window.selectedTab());
    }
    private static void update(MethodPointBlockPair _mp, BreakPointCondition _condition, AbsDebuggerGui _window, GuiStackForm _form, ResultContext _res) {
        _condition.analyze(_mp,_form.getConditional().getText(),_form.getLogs().getText(),_res, _window.getResultContextNext().generateAdv(_res.getContext().getInterrupt()));
        update(_condition, _form);
        if (_form.getDependantPointsForm().getSelectedCurrent().containsObj(MethodPoint.BPC_ENTRY)) {
            _condition.getOthers().add(_mp.getValue().getResultEntry());
        }
        if (_form.getDependantPointsForm().getSelectedCurrent().containsObj(MethodPoint.BPC_EXIT)) {
            _condition.getOthers().add(_mp.getValue().getResultExit());
        }
    }

    static void update(BreakPointCondition _condition, GuiStackForm _form) {
        _condition.getCountModulo().set(_form.getCount().getValue());
        _condition.getCount().set(_form.getCountSub().getValue());
        ExecFileBlockTraceIndex.setAll(_condition.getExclude(),_form.getMustNotBe());
        ExecFileBlockTraceIndex.setAll(_condition.getInclude(),_form.getMustBe());
        _condition.getEnabled().set(_form.getEnabledSub().isSelected());
        _condition.getHit().set(_form.getHit().isSelected());
        _condition.getDisableWhenHit().set(_form.getDisabledWhenHit().isSelected());
        _condition.getSuspend().set(_form.getSuspend().isSelected());
        _condition.getStackLog().set(_form.getStackLog().isSelected());
        _condition.getStackErrLog().set(_form.getStackErrLog().isSelected());
        _condition.getStackResErrLog().set(_form.getStackResErrLog().isSelected());
        _condition.setAll(_form.getDependantPointsForm().getSelected());
        _condition.getPref().set(_form.getPref().getValue());
        _condition.prefsMap(_form.getPrefs().getList());
    }
}
