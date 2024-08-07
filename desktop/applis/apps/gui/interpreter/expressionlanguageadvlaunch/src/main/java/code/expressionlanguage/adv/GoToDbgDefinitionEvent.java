package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.syntax.FileBlockCursor;
import code.expressionlanguage.analyze.syntax.ResultExpressionOperationNode;
import code.expressionlanguage.analyze.syntax.SrcFileLocation;
import code.expressionlanguage.options.ResultContext;
import code.gui.events.AbsActionListener;
import code.gui.images.MetaPoint;
import code.util.CustList;

public final class GoToDbgDefinitionEvent implements AbsActionListener {
    private final AbsDebuggerGui debuggerGui;
    private final ReadOnlyTabEditor tabEditor;
    private final ResultContext resultContext;

    public GoToDbgDefinitionEvent(AbsDebuggerGui _d,ReadOnlyTabEditor _t, ResultContext _r) {
        this.debuggerGui = _d;
        this.tabEditor = _t;
        this.resultContext = _r;
    }

    @Override
    public void action() {
        AnalyzedPageEl page_ = resultContext.getPageEl();
        String relPath_ = tabEditor.getFullPath();
        int caret_ = tabEditor.getCenter().getCaretPosition();
        CustList<SrcFileLocation> l_ = ResultExpressionOperationNode.locations(page_, relPath_, caret_);
        if (l_.size() == 1) {
            FileBlockCursor cursor_ = l_.get(0).cursor();
            FileBlock file_ = cursor_.getFile();
            if (file_ == null) {
                return;
            }
            int index_ = cursor_.getIndex();
            new TabOpeningReadOnlyFile().openFile(debuggerGui,resultContext,file_.getFileName(),file_.getContent());
            int ind_ = debuggerGui.getTabbedPane().getSelectedIndex();
            ReadOnlyTabEditor tab_ = debuggerGui.getTabs().get(ind_);
            MetaPoint p_ = tab_.getCenter().modelToView(index_).getPoint();
            tab_.getScCenter().setHorizontalValue(p_.getXcoord());
            tab_.getScCenter().setVerticalValue(p_.getYcoord());
            tab_.centerSelect(index_,index_);
            tab_.getCenter().visibleCaret();
        }
    }
}
