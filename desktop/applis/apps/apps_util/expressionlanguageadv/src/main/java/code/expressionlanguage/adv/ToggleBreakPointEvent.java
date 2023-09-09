package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.files.AbsSegmentColorPart;
import code.expressionlanguage.exec.dbg.*;
import code.expressionlanguage.options.ResultContext;
import code.gui.AbsAttrSet;
import code.gui.AbsTextPane;
import code.gui.GuiConstants;
import code.gui.events.AbsActionListener;
import code.gui.initialize.AbsCompoFactory;

public final class ToggleBreakPointEvent implements AbsActionListener {
    private final AbsDebuggerGui window;
    private final ReadOnlyTabEditor tabEditor;
    private final ResultContext currentResult;

    public ToggleBreakPointEvent(AbsDebuggerGui _dbg, ReadOnlyTabEditor _t, ResultContext _c) {
        this.window = _dbg;
        this.tabEditor = _t;
        currentResult = _c;
    }

    @Override
    public void action() {
        AbsPairPoint pair_ = currentResult.tryGetPair(tabEditor.getFullPath(), tabEditor.getCenter().getCaretPosition());
        AbsPairPoint before_ = state(currentResult,pair_);
        currentResult.toggleBreakPoint(tabEditor.getFullPath(), tabEditor.getCenter().getCaretPosition());
        AbsPairPoint after_ = state(currentResult,pair_);
        FramePoints fp_ = window.getFramePoints();
        ToggleBreakPointEnabledEvent.removeIfUsed(before_, after_, fp_);
        fp_.refreshBp(currentResult);
        fp_.refreshMethod(currentResult);
        fp_.refreshWatch(currentResult);
        fp_.getCommonFrame().pack();
        afterToggle(currentResult, tabEditor);
    }

    static AbsPairPoint state(ResultContext _r, AbsPairPoint _pair) {
        if (_pair instanceof BreakPointBlockPair) {
            BreakPointBlockKey k_ = ((BreakPointBlockPair) _pair).getBp();
            return _r.getPair(k_.getFile(),k_.getOffset());
        }
        if (_pair instanceof MethodPointBlockPair) {
            MethodPointBlockKey k_ = ((MethodPointBlockPair) _pair).getMp();
            return _r.getPair(k_.keyStr());
        }
        if (_pair instanceof WatchPointBlockPair) {
            WatchPointBlockKey k_ = ((WatchPointBlockPair) _pair).getWp();
            RootBlock r_ = ((WatchPointBlockPair) _pair).getRoot();
            if (r_ == null) {
                return null;
            }
            return _r.getPairWatch(k_.isTrueField(), r_.getNumberAll(),k_.fieldName());
        }
        return null;
    }

    static void afterToggle(ResultContext _r, ReadOnlyTabEditor _tab) {
        if (_tab == null) {
            return;
        }
        FileBlock file_ = _r.getPageEl().getPreviousFilesBodies().getVal(_tab.getFullPath());
        String cont_ = file_.getContent();
        colors(new SegmentFindPart(0,cont_.length()), _tab.getCompoFactory(), _tab.getCenter(), GuiConstants.BLACK);
        for (SegmentReadOnlyPart s: DbgSyntaxColoring.partsBpMpWp(_r, file_)) {
            colors(s, _tab.getCompoFactory(), _tab.getCenter(), GuiConstants.RED);
        }
    }

    static void colors(AbsSegmentColorPart _parts, AbsCompoFactory _compos, AbsTextPane _area, int _bk) {
        AbsAttrSet as_ = _compos.newAttrSet();
        as_.addBackground(_bk);
        as_.addForeground(GuiConstants.WHITE);
        as_.addFontSize(12);
        _compos.invokeNow(new SetCharacterAttributes(_area, _parts.getBegin(), _parts.getEnd() - _parts.getBegin(),as_));
    }

}
