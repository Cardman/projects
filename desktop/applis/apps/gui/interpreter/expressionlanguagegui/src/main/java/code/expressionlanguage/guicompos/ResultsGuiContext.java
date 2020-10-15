package code.expressionlanguage.guicompos;

import code.expressionlanguage.analyze.ReportedMessages;

public final class ResultsGuiContext {
    private final GuiContextEl runnable;
    private final ReportedMessages reportedMessages;

    public ResultsGuiContext(GuiContextEl _runnable, ReportedMessages _reportedMessages) {
        this.runnable = _runnable;
        this.reportedMessages = _reportedMessages;
    }

    public GuiContextEl getRunnable() {
        return runnable;
    }
    public ReportedMessages getReportedMessages() {
        return reportedMessages;
    }
}
