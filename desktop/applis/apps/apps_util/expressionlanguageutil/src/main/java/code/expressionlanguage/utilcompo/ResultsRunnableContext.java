package code.expressionlanguage.utilcompo;

import code.expressionlanguage.analyze.ReportedMessages;

public final class ResultsRunnableContext {
    private final RunnableContextEl runnable;
    private final ReportedMessages reportedMessages;

    public ResultsRunnableContext(RunnableContextEl runnable, ReportedMessages reportedMessages) {
        this.runnable = runnable;
        this.reportedMessages = reportedMessages;
    }

    public RunnableContextEl getRunnable() {
        return runnable;
    }
    public ReportedMessages getReportedMessages() {
        return reportedMessages;
    }

}
