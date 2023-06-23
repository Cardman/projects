package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.syntax.ResultExpressionOperationNode;
import code.expressionlanguage.analyze.syntax.SrcFileLocation;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.utilimpl.RunningTest;
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
        ResultContext resUser_ = RunningTest.nextValidateQuick(mainFrame_.getResultContextNext(),base_, added_);
        if (resUser_ == null) {
            return;
        }
        AnalyzedPageEl page_ = resUser_.getPageEl();
        String relPath_ = tabEditor.getRelPath();
        int caret_ = tabEditor.getCenter().getCaretPosition();
        CustList<SrcFileLocation> l_ = ResultExpressionOperationNode.locations(page_, relPath_, caret_);
        edi_.afterSearchSymbol(page_, relPath_, caret_, l_, ResultExpressionOperationNode.export(page_, l_));
    }
}
