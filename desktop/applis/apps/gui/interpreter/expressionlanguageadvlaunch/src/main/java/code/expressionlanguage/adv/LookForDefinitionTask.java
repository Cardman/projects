package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.syntax.ResultExpressionOperationNode;
import code.expressionlanguage.analyze.syntax.SrcFileLocation;
import code.expressionlanguage.options.ResultContext;
import code.util.CustList;

public final class LookForDefinitionTask implements Runnable {
    private final TabEditor tabEditor;

    public LookForDefinitionTask(TabEditor _t) {
        this.tabEditor = _t;
    }

    @Override
    public void run() {
        WindowWithTreeImpl edi_ = tabEditor.getWindowSecEditor();
        ResultContext r_ = edi_.getMainFrame().getUserResultGene();
        if (r_ == null) {
            return;
        }
        AnalyzedPageEl page_ = r_.getPageEl();
        String relPath_ = tabEditor.getRelPath();
        int caret_ = tabEditor.getCenter().getCaretPosition();
        CustList<SrcFileLocation> l_ = ResultExpressionOperationNode.locations(page_, tabEditor.getRelPath(), tabEditor.getCenter().getCaretPosition());
        edi_.afterSearchSymbol(page_, relPath_, caret_, l_, ResultExpressionOperationNode.export(page_, l_));
    }
}
