package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.AbsPairPoint;
import code.expressionlanguage.exec.dbg.BreakPointBlockPair;
import code.expressionlanguage.exec.dbg.MethodPointBlockPair;
import code.expressionlanguage.options.ResultContext;
import code.gui.events.AbsActionListener;

public final class ToggleBreakPointEnabledEvent implements AbsActionListener {
    private final AbsDebuggerGui window;
    private final ReadOnlyTabEditor tabEditor;
    private final ResultContext currentResult;

    public ToggleBreakPointEnabledEvent(AbsDebuggerGui _dbg, ReadOnlyTabEditor _t, ResultContext _res) {
        this.window = _dbg;
        this.tabEditor = _t;
        this.currentResult = _res;
    }

    @Override
    public void action() {
        currentResult.toggleBreakPointEnabled(tabEditor.getFullPath(), tabEditor.getCenter().getCaretPosition());
        AbsPairPoint pair_ = currentResult.tryGetPair(tabEditor.getFullPath(), tabEditor.getCenter().getCaretPosition());
        FramePoints fp_ = window.getFramePoints();
        if (pair_ instanceof BreakPointBlockPair) {
            BreakPointBlockPair bp_ = fp_.getFrameBpFormContent().getSelectedBp();
            if (bp_ != null && ((BreakPointBlockPair)pair_).getBp().match(bp_.getBp())) {
                fp_.getFrameBpFormContent().getEnabledBp().setSelected(bp_.getValue().isEnabled());
            }
        }
        if (pair_ instanceof MethodPointBlockPair) {
            MethodPointBlockPair mp_ = fp_.getFrameFormContent().getSelectedMp();
            if (mp_ != null && ((MethodPointBlockPair)pair_).getMp().match(mp_.getMp())) {
                fp_.getFrameFormContent().getEnabledMp().setSelected(mp_.getValue().isEnabled());
            }
        }
        ToggleWatchPointEnabledEvent.refreshEnabled(fp_, pair_);
        fp_.refreshBp(window, currentResult);
        fp_.refreshMethod(window, currentResult);
        fp_.refreshWatch(window, currentResult);
        fp_.getCommonFrame().pack();
        ToggleBreakPointEvent.afterToggle(currentResult, tabEditor);
    }

}
