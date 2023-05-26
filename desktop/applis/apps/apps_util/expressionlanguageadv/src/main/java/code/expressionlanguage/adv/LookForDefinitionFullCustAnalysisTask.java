package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.syntax.ResultExpressionOperationNode;
import code.expressionlanguage.analyze.syntax.SrcFileLocation;
import code.expressionlanguage.guicompos.LgNamesGui;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.utilcompo.FileInfos;
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
        if (base_ == null) {
            return;
        }
        LgNamesGui lg_ = (LgNamesGui) base_.getForwards().getGenerator();
        FileInfos fileInfos_ = lg_.getExecContent().getInfos();
        StringMap<String> added_ = AnalyzeExpressionSource.addedExp(mainFrame_);
        ResultContext resUser_ = RunningTest.nextValidateQuick(base_, lg_, lg_.getExecContent().getExecutingOptions(), fileInfos_, added_);
        AnalyzedPageEl page_ = resUser_.getPageEl();
        String relPath_ = tabEditor.getRelPath();
        int caret_ = tabEditor.getCenter().getCaretPosition();
        CustList<SrcFileLocation> l_ = ResultExpressionOperationNode.locations(page_, relPath_, caret_);
        edi_.afterSearchSymbol(page_, relPath_, caret_, l_, ResultExpressionOperationNode.export(page_, l_));
    }
}
