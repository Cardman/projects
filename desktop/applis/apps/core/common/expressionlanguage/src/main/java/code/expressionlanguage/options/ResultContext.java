package code.expressionlanguage.options;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.fwd.Forwards;

public final class ResultContext {
    private final AnalyzedPageEl pageEl;
    private ContextEl context;
    private final Forwards forwards;
    private final ReportedMessages reportedMessages;

    public ResultContext(AnalyzedPageEl _page, Forwards _fwd, ReportedMessages _reportedMessages) {
        pageEl = _page;
        this.forwards = _fwd;
        this.reportedMessages = _reportedMessages;
    }

    public void setContext(ContextEl _c) {
        this.context = _c;
    }

    public AnalyzedPageEl getPageEl() {
        return pageEl;
    }

    public Forwards getForwards() {
        return forwards;
    }

    public ContextEl getContext() {
        return context;
    }

    public ReportedMessages getReportedMessages() {
        return reportedMessages;
    }
}
