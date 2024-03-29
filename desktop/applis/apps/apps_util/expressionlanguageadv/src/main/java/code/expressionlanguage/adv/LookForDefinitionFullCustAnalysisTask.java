package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.syntax.ResultExpressionOperationNode;
import code.expressionlanguage.analyze.syntax.SrcFileLocation;
import code.expressionlanguage.options.ResultContext;
import code.util.CustList;
import code.util.StringMap;

public final class LookForDefinitionFullCustAnalysisTask implements Runnable {
    private final TabEditor tabEditor;

    public LookForDefinitionFullCustAnalysisTask(TabEditor _t) {
        this.tabEditor = _t;
    }

    @Override
    public void run() {
        WindowWithTreeImpl edi_ = tabEditor.getWindowSecEditor();
        WindowCdmEditor mainFrame_ = edi_.getMainFrame();
        ResultContext base_ = mainFrame_.getBaseResult();
        StringMap<String> added_ = AnalyzeExpressionSource.addedExp(mainFrame_);
        AnalyzedPageEl page_ = mainFrame_.getResultContextNext().nextAna(base_, mainFrame_.getResultContextNext().files(base_,added_));
        String relPath_ = tabEditor.getRelPath();
        int caret_ = tabEditor.getCenter().getCaretPosition();
        CustList<SrcFileLocation> l_ = ResultExpressionOperationNode.locations(page_, relPath_, caret_);
        edi_.afterSearchSymbol(page_, relPath_, caret_, l_, ResultExpressionOperationNode.export(page_, l_));
    }
}
