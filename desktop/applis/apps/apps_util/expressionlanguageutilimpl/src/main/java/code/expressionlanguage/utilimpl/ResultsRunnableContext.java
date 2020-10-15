package code.expressionlanguage.utilimpl;

import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.utilcompo.RunnableContextEl;

public final class ResultsRunnableContext {
    private final RunnableContextEl runnable;
    private final ReportedMessages reportedMessages;

    public ResultsRunnableContext(RunnableContextEl _runnable, ReportedMessages _reportedMessages) {
        this.runnable = _runnable;
        this.reportedMessages = _reportedMessages;
    }

    public RunnableContextEl getRunnable() {
        return runnable;
    }
    public ReportedMessages getReportedMessages() {
        return reportedMessages;
    }

}
