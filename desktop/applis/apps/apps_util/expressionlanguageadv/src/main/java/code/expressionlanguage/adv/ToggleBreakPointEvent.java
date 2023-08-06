package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.files.AbsSegmentColorPart;
import code.expressionlanguage.options.ResultContext;
import code.gui.AbsAttrSet;
import code.gui.AbsTextPane;
import code.gui.GuiConstants;
import code.gui.events.AbsActionListener;
import code.gui.initialize.AbsCompoFactory;

public final class ToggleBreakPointEvent implements AbsActionListener {
    private final ReadOnlyTabEditor tabEditor;

    public ToggleBreakPointEvent(ReadOnlyTabEditor _t) {
        this.tabEditor = _t;
    }

    @Override
    public void action() {
        ResultContext r_ = tabEditor.getDebuggerGui().getCurrentResult();
        r_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint(tabEditor.getFullPath(), tabEditor.getCenter().getCaretPosition(), r_);
        afterToggle(r_, tabEditor);
    }

    static void afterToggle(ResultContext _r, ReadOnlyTabEditor _tab) {
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
