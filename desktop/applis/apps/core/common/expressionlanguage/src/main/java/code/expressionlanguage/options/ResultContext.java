package code.expressionlanguage.options;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.ReportedMessages;

public final class ResultContext {
    private final ContextEl context;
    private final ReportedMessages reportedMessages;

    public ResultContext(ContextEl _context, ReportedMessages _reportedMessages) {
        this.context = _context;
        this.reportedMessages = _reportedMessages;
    }

    public ContextEl getContext() {
        return context;
    }

    public ReportedMessages getReportedMessages() {
        return reportedMessages;
    }
}
