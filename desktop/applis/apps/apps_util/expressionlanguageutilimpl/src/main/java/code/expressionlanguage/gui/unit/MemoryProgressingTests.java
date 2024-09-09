package code.expressionlanguage.gui.unit;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.ExecutingOptions;
import code.expressionlanguage.utilcompo.FileInfos;
import code.expressionlanguage.utilcompo.LgNamesWithNewAliases;
import code.stream.BytesInfo;

public final class MemoryProgressingTests extends ProgressingTestsAbs {
    private BytesInfo exportedReport = new BytesInfo(new byte[0],true);
    public MemoryProgressingTests(TestableFrame _mainWindow) {
        super(_mainWindow);
    }

    @Override
    public void showErrors(ReportedMessages _reportedMessages, Options _opts, ExecutingOptions _exec, FileInfos _infos) {
        exportedReport = exportedErrors(_reportedMessages, _exec, _infos);
    }

    @Override
    public void setResults(ContextEl _ctx, ExecutingOptions _ex, Struct _res, LgNamesWithNewAliases _evolved) {
        exportedReport = exportedResults(_ctx,_ex, _res, _evolved);
    }

    @Override
    public BytesInfo getExportedReport() {
        return exportedReport;
    }
}
