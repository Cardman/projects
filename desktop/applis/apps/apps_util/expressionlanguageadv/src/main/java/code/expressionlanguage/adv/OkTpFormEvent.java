package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.*;
import code.expressionlanguage.options.ResultContext;
import code.gui.events.AbsActionListener;

public final class OkTpFormEvent implements AbsActionListener {
    private final AbsDebuggerGui window;
    private final ResultContext currentResult;

    public OkTpFormEvent(AbsDebuggerGui _w, ResultContext _res) {
        this.window = _w;
        currentResult = _res;
    }

    @Override
    public void action() {
        TypePointBlockPair bp_ = window.getFramePoints().getFrameTpFormContent().getSelectedTp();
        if (bp_ == null) {
            AbsPairPoint p_ = currentResult.tryGetPair(window.getFramePoints().getFrameTpFormContent().getFileName().getText(), window.getFramePoints().getFrameTpFormContent().getCaret().getValue());
            if (!(p_ instanceof TypePointBlockPair)) {
                return;
            }
            bp_ = (TypePointBlockPair) p_;
            currentResult.tpList().add(bp_);
        }
        bpAction(window, window.getFramePoints().getFrameTpFormContent(), bp_, currentResult);
        window.getFramePoints().guiContentBuildClear();
        window.getFramePoints().refreshTp(currentResult);
        window.getFramePoints().getCommonFrame().pack();
        ToggleBreakPointEvent.afterToggle(currentResult,window.selectedTab());
    }

    static void bpAction(AbsDebuggerGui _win, FrameTpFormContent _bp, TypePointBlockPair _selected, ResultContext _res) {
        _selected.getValue().setEnabled(_bp.getEnabledBp().isSelected());
        _selected.getValue().setInstanceType(_bp.getInstanceType().isSelected());
        _selected.getValue().setStaticType(_bp.getStaticType().isSelected());
        update(_selected, _selected.getValue().getResultInstance(), _win, _bp.getGuiInsStackForm(), _res);
        update(_selected, _selected.getValue().getResultStatic(), _win, _bp.getGuiStaStackForm(), _res);
        _bp.setSelectedTp(null);
    }

    private static void update(TypePointBlockPair _mp, BreakPointCondition _condition, AbsDebuggerGui _window, GuiStackForm _form, ResultContext _curr) {
        _condition.analyze(_mp,_form.getConditional().getText(),_form.getLogs().getText(), _form.getWatches().getText(), _curr, _window.getResultContextNext().generateAdv(_curr.getContext().getInterrupt()));
        OkMpFormEvent.update(_condition,_form);
        if (_form.getDependantPointsForm().getSelectedCurrent().containsObj(BreakPoint.BPC_STATIC)) {
            _condition.getOthers().add(_mp.getValue().getResultStatic());
        }
        if (_form.getDependantPointsForm().getSelectedCurrent().containsObj(BreakPoint.BPC_INSTANCE)) {
            _condition.getOthers().add(_mp.getValue().getResultInstance());
        }
    }
}
