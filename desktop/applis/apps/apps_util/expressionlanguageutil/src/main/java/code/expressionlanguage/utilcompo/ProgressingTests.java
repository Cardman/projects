package code.expressionlanguage.utilcompo;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.structs.Struct;
import code.gui.CdmFactory;
import code.stream.BytesInfo;
import code.threads.AbstractAtomicBoolean;

public interface ProgressingTests {
    CdmFactory getFactory();
    void showWarnings(ContextEl _ctx, ReportedMessages _reportedMessages, Options _opts, ExecutingOptions _exec, FileInfos _infos);
    void showErrors(ReportedMessages _reportedMessages, Options _opts, ExecutingOptions _exec, FileInfos _infos);
    void updateInfos(ContextEl _ctx, Struct _infos, LgNamesWithNewAliases _evolved);
    void finish(ContextEl _ctx, Struct _infos, LgNamesWithNewAliases _evolved);
    void setResults(ContextEl _ctx, ExecutingOptions _ex, Struct _res, LgNamesWithNewAliases _evolved);
    AbstractAtomicBoolean getStop();
    void setStop(AbstractAtomicBoolean _a);
    BytesInfo getExportedReport();
    InterruptibleContextEl ctx();
    void ctx(InterruptibleContextEl _ctx);
}
