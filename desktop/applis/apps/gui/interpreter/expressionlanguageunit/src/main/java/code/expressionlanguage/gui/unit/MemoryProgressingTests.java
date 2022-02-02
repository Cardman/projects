package code.expressionlanguage.gui.unit;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.utilcompo.*;

public final class MemoryProgressingTests extends ProgressingTestsAbs {
    private byte[] exportedReport = new byte[0];
    public MemoryProgressingTests(TestableFrame _mainWindow) {
        super(_mainWindow);
    }

    @Override
    public void showErrors(ReportedMessages _reportedMessages, Options _opts, ExecutingOptions _exec, FileInfos _infos) {
        exportedReport = exportedErrors(_reportedMessages, _exec, _infos);
    }

    @Override
    public void setResults(RunnableContextEl _ctx, Argument _res, LgNamesWithNewAliases _evolved) {
        exportedReport = exportedResults(_ctx, _res, _evolved);
    }

    @Override
    public byte[] getExportedReport() {
        return exportedReport;
    }
}
