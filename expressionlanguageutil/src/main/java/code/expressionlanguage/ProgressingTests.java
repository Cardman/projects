package code.expressionlanguage;

import code.expressionlanguage.structs.Struct;

public interface ProgressingTests {
    void showErrors(RunnableContextEl _ctx, ExecutingOptions _exec);
    void updateInfos(RunnableContextEl _ctx, Struct _infos, Struct _doneTests, Struct _method, Struct _count);
    void finish(RunnableContextEl _ctx, Struct _infos);
    void setResults(RunnableContextEl _ctx, Argument _res);
}
