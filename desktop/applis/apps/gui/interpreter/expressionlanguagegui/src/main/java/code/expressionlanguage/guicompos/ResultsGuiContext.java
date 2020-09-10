package code.expressionlanguage.guicompos;

import code.expressionlanguage.analyze.ReportedMessages;

public final class ResultsGuiContext {
    private final GuiContextEl runnable;
    private final ReportedMessages reportedMessages;

    public ResultsGuiContext(GuiContextEl runnable, ReportedMessages reportedMessages) {
        this.runnable = runnable;
        this.reportedMessages = reportedMessages;
    }

    public GuiContextEl getRunnable() {
        return runnable;
    }
    public ReportedMessages getReportedMessages() {
        return reportedMessages;
    }
}
