package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.AbsPairPoint;
import code.expressionlanguage.exec.dbg.BreakPoint;
import code.expressionlanguage.exec.dbg.BreakPointBlockPair;
import code.expressionlanguage.exec.dbg.BreakPointCondition;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.options.ResultContext;
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
        window.getFramePoints().refreshBp(currentResult);
        window.getFramePoints().getCommonFrame().pack();
        ToggleBreakPointEvent.afterToggle(currentResult,window.selectedTab());
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
        _condition.analyze(_mp,_form.getConditional().getText(),_form.getLogs().getText(),_curr, _window.getResultContextNext().generateAdv(_curr.getContext().getInterrupt()), _flag);
        OkMpFormEvent.update(_condition,_form);
        if (_form.getDependantPointsForm().getSelectedCurrent().containsObj(BreakPoint.BPC_STD)) {
            _condition.getOthers().add(_mp.getValue().getResultStd());
        }
        if (_form.getDependantPointsForm().getSelectedCurrent().containsObj(BreakPoint.BPC_STATIC)) {
            _condition.getOthers().add(_mp.getValue().getResultStatic());
        }
        if (_form.getDependantPointsForm().getSelectedCurrent().containsObj(BreakPoint.BPC_INSTANCE)) {
            _condition.getOthers().add(_mp.getValue().getResultInstance());
        }
    }
}
