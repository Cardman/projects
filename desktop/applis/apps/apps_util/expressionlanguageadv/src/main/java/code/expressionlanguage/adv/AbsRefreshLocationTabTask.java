package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.syntax.ResultExpressionOperationNode;
import code.expressionlanguage.analyze.syntax.RowSrcLocation;
import code.expressionlanguage.analyze.syntax.SrcFileLocation;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.utilimpl.RunningTest;
import code.gui.AbsPanel;
import code.util.CustList;
import code.util.StringMap;

public abstract class AbsRefreshLocationTabTask implements Runnable {
    private final AbsPanel event;
    private final WindowWithTreeImpl window;
    private final ResultRowSrcLocationList result;

    protected AbsRefreshLocationTabTask(AbsPanel _e, WindowWithTreeImpl _w, ResultRowSrcLocationList _result) {
        this.event = _e;
        this.window = _w;
        this.result = _result;
    }

    @Override
    public void run() {
        WindowCdmEditor mainFrame_ = window.getMainFrame();
        ResultContext base_ = mainFrame_.getBaseResult();
        StringMap<String> added_ = AnalyzeExpressionSource.addedExp(mainFrame_);
        ResultContext resUser_ = RunningTest.nextValidateQuick(mainFrame_.getResultContextNext(),base_, added_);
        if (resUser_ == null) {
            return;
        }
        AnalyzedPageEl page_ = resUser_.getPageEl();
        String relPath_ = path();
        int caret_ = caret();
        CustList<SrcFileLocation> l_ = ResultExpressionOperationNode.locations(page_, relPath_, caret_);
        CustList<RowSrcLocation> s_ = ResultExpressionOperationNode.export(page_, l_);
        ResultRowSrcLocationList r_ = new ResultRowSrcLocationList(page_, relPath_, caret_, l_, s_);
        event.removeAll();
        window.updatePanel(event,r_);
        window.update(r_);
    }

    public ResultRowSrcLocationList getResult() {
        return result;
    }

    protected abstract String path();
    protected abstract int caret();
}
