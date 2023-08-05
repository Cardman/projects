package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.syntax.ResultExpressionOperationNode;
import code.expressionlanguage.common.FileMetrics;
import code.expressionlanguage.exec.blocks.ExecFileBlock;
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
        ExecFileBlock f_ = _r.getForwards().dbg().getFiles().getVal(file_);
        int o_ = ResultExpressionOperationNode.beginPart(_tab.getCenter().getCaretPosition(), file_);
        FileMetrics m_ = file_.getMetrics(_tab.getTabWidth());
        int min_ = _tab.centerText().lastIndexOf('\n',o_);
        int max_ = _tab.centerText().indexOf('\n',o_);
        if (!_r.getContext().getClasses().getDebugMapping().getBreakPointsBlock().bp(f_, m_, o_).isEmpty()) {
            colors(new SegmentFindPart(min_,max_), _tab.getCompoFactory(), _tab.getCenter(), GuiConstants.RED);
        } else {
            colors(new SegmentFindPart(min_,max_), _tab.getCompoFactory(), _tab.getCenter(), GuiConstants.BLACK);
        }
    }

    static void colors(SegmentFindPart _parts, AbsCompoFactory _compos, AbsTextPane _area, int _bk) {
        AbsAttrSet as_ = _compos.newAttrSet();
        as_.addBackground(_bk);
        as_.addForeground(GuiConstants.WHITE);
        as_.addFontSize(12);
        _compos.invokeNow(new SetCharacterAttributes(_area, _parts.getBegin(), _parts.getEnd() - _parts.getBegin(),as_));
    }

}
