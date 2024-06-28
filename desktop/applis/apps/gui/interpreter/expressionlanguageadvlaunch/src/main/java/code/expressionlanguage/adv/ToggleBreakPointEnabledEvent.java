package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.*;
import code.expressionlanguage.options.ResultContext;
import code.gui.AbsCustCheckBox;
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
        updateSelectedChecked(pair_, fp_);
        fp_.refreshBp(currentResult);
        fp_.refreshTp(currentResult);
        fp_.getCommonFrame().pack();
        ToggleBreakPointEvent.afterToggle(currentResult, tabEditor);
    }

    static void removeIfUsed(AbsPairPoint _before, AbsPairPoint _after, FramePoints _fp) {
        if (_after == null) {
            AbsPairPoint sec_ = matchesSec(_fp, _before);
            if (matches(_fp, _before, sec_) != null) {
                _fp.getFrameBpFormContent().setSelectedBp(null);
                _fp.getFrameTpFormContent().setSelectedTp(null);
                _fp.getFrameFormContent().setSelectedMp(null);
                _fp.getFrameWpFormContent().setSelectedWp(null);
                _fp.guiContentBuildClear();
            }
        }
    }

    static void updateSelectedChecked(AbsPairPoint _pair, FramePoints _fp) {
        AbsPairPoint sec_ = matchesSec(_fp, _pair);
        AbsCustCheckBox ch_ = matches(_fp, _pair, sec_);
        boolean cl_ = click(sec_);
        if (ch_ != null) {
            ch_.setSelected(cl_);
        }
    }

    static AbsPairPoint matchesSec(FramePoints _fp, AbsPairPoint _one) {
        if (_one instanceof BreakPointBlockPair) {
            return _fp.getFrameBpFormContent().getSelectedBp();
        }
        if (_one instanceof TypePointBlockPair) {
            return _fp.getFrameTpFormContent().getSelectedTp();
        }
        if (_one instanceof MethodPointBlockPair) {
            return _fp.getFrameFormContent().getSelectedMp();
        }
        if (_one instanceof WatchPointBlockPair) {
            return _fp.getFrameWpFormContent().getSelectedWp();
        }
        return null;
    }

    static boolean click(AbsPairPoint _one) {
        if (_one instanceof BreakPointBlockPair) {
            return ((BreakPointBlockPair) _one).getValue().isEnabled();
        }
        if (_one instanceof TypePointBlockPair) {
            return ((TypePointBlockPair) _one).getValue().isEnabled();
        }
        if (_one instanceof MethodPointBlockPair) {
            return ((MethodPointBlockPair) _one).getValue().isEnabled();
        }
        if (_one instanceof WatchPointBlockPair) {
            return ((WatchPointBlockPair) _one).getValue().isEnabled();
        }
        return false;
    }
    static AbsCustCheckBox matches(FramePoints _fp, AbsPairPoint _one, AbsPairPoint _two) {
        if (_one instanceof BreakPointBlockPair && _two instanceof BreakPointBlockPair && ((BreakPointBlockPair) _one).getBp().match(((BreakPointBlockPair) _two).getBp())) {
            return _fp.getFrameBpFormContent().getEnabledBp();
        }
        if (_one instanceof TypePointBlockPair && _two instanceof TypePointBlockPair && ((TypePointBlockPair) _one).getBp().match(((TypePointBlockPair) _two).getBp())) {
            return _fp.getFrameTpFormContent().getEnabledBp();
        }
        if (_one instanceof MethodPointBlockPair && _two instanceof MethodPointBlockPair && ((MethodPointBlockPair) _one).getMp().match(((MethodPointBlockPair) _two).getMp())) {
            return _fp.getFrameFormContent().getEnabledMp();
        }
        if (_one instanceof WatchPointBlockPair && _two instanceof WatchPointBlockPair && ((WatchPointBlockPair) _one).getWp().match(((WatchPointBlockPair) _two).getWp())) {
            return _fp.getFrameWpFormContent().getEnabledWp();
        }
        return null;
    }
}
