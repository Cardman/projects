package code.expressionlanguage.utilcompo;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.structs.Struct;

public interface ProgressingTests {
    AbstractInterceptor getInterceptor();
    void showWarnings(RunnableContextEl _ctx, ReportedMessages _reportedMessages, Options _opts, ExecutingOptions _exec, FileInfos _infos);
    void showErrors(ReportedMessages _reportedMessages, Options _opts, ExecutingOptions _exec, FileInfos _infos);
    void updateInfos(RunnableContextEl _ctx, Struct _infos, Struct _doneTests, Struct _method, Struct _count, LgNamesWithNewAliases _evolved);
    void finish(RunnableContextEl _ctx, Struct _infos, LgNamesWithNewAliases _evolved);
    void setResults(RunnableContextEl _ctx, Argument _res, LgNamesWithNewAliases _evolved);
    ExecutingOptions getExec();
    void init(ExecutingOptions _exec);
}
