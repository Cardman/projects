package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.files.AbsSegmentColorPart;
import code.expressionlanguage.common.SynthFieldInfo;
import code.expressionlanguage.exec.dbg.*;
import code.expressionlanguage.options.ResultContext;
import code.gui.AbsAttrSet;
import code.gui.AbsTextPane;
import code.gui.GuiConstants;
import code.gui.events.AbsActionListener;
import code.gui.initialize.AbsCompoFactory;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;

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
        fp_.refreshTp(currentResult);
        fp_.getCommonFrame().pack();
        afterToggle(currentResult, tabEditor);
    }

    static AbsPairPoint state(ResultContext _r, AbsPairPoint _pair) {
        if (_pair instanceof BreakPointBlockPair) {
            BreakPointBlockKey k_ = ((BreakPointBlockPair) _pair).getBp();
            return _r.getPair(k_.getFile(),k_.getOffset());
        }
        if (_pair instanceof TypePointBlockPair) {
            BreakPointBlockKey k_ = ((TypePointBlockPair) _pair).getBp();
            return _r.getPairType(k_.getFile(),k_.getOffset());
        }
        if (_pair instanceof MethodPointBlockPair) {
            MethodPointBlockKey k_ = ((MethodPointBlockPair) _pair).getMp();
            return _r.getPair(k_.keyStr());
        }
        if (_pair instanceof WatchPointBlockPair) {
            WatchPointBlockKey k_ = ((WatchPointBlockPair) _pair).getWp();
            RootBlock r_ = ((WatchPointBlockPair) _pair).getRoot();
            return _r.getPairWatch(k_.isTrueField(), SynthFieldInfo.nb(r_),k_.fieldName());
        }
        return null;
    }

    static void afterToggle(ResultContext _r, ReadOnlyTabEditor _tab) {
        if (_tab == null) {
            return;
        }
        FileBlock file_ = _r.getPageEl().getPreviousFilesBodies().getVal(_tab.getFullPath());
        IdMap<SyntaxRefTokenEnum, CustList<SegmentReadOnlyTokenPart>> s_ = new IdMap<SyntaxRefTokenEnum, CustList<SegmentReadOnlyTokenPart>>();
        for (EntryCust<FileBlock, IdMap<SyntaxRefTokenEnum, CustList<SegmentReadOnlyTokenPart>>> e: _tab.getDebuggerGui().getSyntax().entryList()) {
            if (e.getKey() == file_) {
                s_ = e.getValue();
                break;
            }
        }
        if (file_ == null) {
            return;
        }
        String cont_ = file_.getContent();
        AbsCompoFactory compoFactory_ = _tab.getCompoFactory();
        AbsTextPane center_ = _tab.getCenter();
        colors(new SegmentFindPart(0,cont_.length()), compoFactory_, center_, GuiConstants.BLACK);
        for (EntryCust<SyntaxRefTokenEnum,CustList<SegmentReadOnlyTokenPart>> e: s_.entryList()) {
            AbsAttrSet as_ = compoFactory_.newAttrSet();
            as_.addFontSize(12);
            as_.addBackground(GuiConstants.BLACK);
            as_.addForeground(color(e.getKey()));
            as_.addItalic(italic(e.getKey()));
            for (SegmentReadOnlyTokenPart s: e.getValue()) {
                 compoFactory_.invokeNow(new SetCharacterAttributes(center_, s.getBegin(), s.getEnd() - s.getBegin(),as_));
            }
        }
        for (SegmentReadOnlyPart s: DbgSyntaxColoring.partsBpMpWp(_r, file_)) {
            colors(s, compoFactory_, center_, GuiConstants.RED);
        }
    }
    static int color(SyntaxRefTokenEnum _s) {
        if (_s == SyntaxRefTokenEnum.ANNOT_FIELD || _s == SyntaxRefTokenEnum.INST_FIELD || _s == SyntaxRefTokenEnum.STATIC_FIELD) {
            return GuiConstants.newColor(9*16+8,7*16+6,10*16+10);
        }
        if (_s == SyntaxRefTokenEnum.ANNOT_FIELD_PRED || _s == SyntaxRefTokenEnum.INST_FIELD_PRED || _s == SyntaxRefTokenEnum.STATIC_FIELD_PRED) {
            return GuiConstants.newColor(10*16+10,7*16+6, 9*16+8);
        }
        if (_s == SyntaxRefTokenEnum.FCT || _s == SyntaxRefTokenEnum.FCT_STAT || _s == SyntaxRefTokenEnum.FCT_STAT_CALL || _s == SyntaxRefTokenEnum.OPERATOR) {
            return GuiConstants.newColor(15*16+15,13*16+6,6*16+11);
        }
        if (_s == SyntaxRefTokenEnum.FCT_PRED || _s == SyntaxRefTokenEnum.FCT_STAT_PRED || _s == SyntaxRefTokenEnum.FCT_STAT_CALL_PRED || _s == SyntaxRefTokenEnum.OPERATOR_PRED) {
            return GuiConstants.newColor(10*16+9,12*16+7, 13*16+6);
        }
        if (_s == SyntaxRefTokenEnum.TO_STR || _s == SyntaxRefTokenEnum.RAND) {
            return GuiConstants.newColor(6*16+11,13*16+6,15*16+15);
        }
        if (_s == SyntaxRefTokenEnum.TO_STR_PRED || _s == SyntaxRefTokenEnum.RAND_PRED) {
            return GuiConstants.newColor(13*16+6,12*16+7, 10*16+9);
        }
        return GuiConstants.newColor(13*16+13,7*16+8,3*16+2);
    }

    static boolean italic(SyntaxRefTokenEnum _s) {
        return _s == SyntaxRefTokenEnum.STATIC_FIELD || _s == SyntaxRefTokenEnum.STATIC_FIELD_PRED || _s == SyntaxRefTokenEnum.FCT_STAT || _s == SyntaxRefTokenEnum.FCT_STAT_CALL || _s == SyntaxRefTokenEnum.FCT_STAT_PRED || _s == SyntaxRefTokenEnum.FCT_STAT_CALL_PRED;
    }

    static void colors(AbsSegmentColorPart _parts, AbsCompoFactory _compos, AbsTextPane _area, int _bk) {
        AbsAttrSet as_ = _compos.newAttrSet();
        as_.addBackground(_bk);
        as_.addForeground(GuiConstants.WHITE);
        as_.addFontSize(12);
        _compos.invokeNow(new SetCharacterAttributes(_area, _parts.getBegin(), _parts.getEnd() - _parts.getBegin(),as_));
    }

}
