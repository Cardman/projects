package code.expressionlanguage.utilcompo;

import code.expressionlanguage.Argument;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.structs.Struct;

public interface ProgressingTests {
    void showErrors(RunnableContextEl _ctx, Options _opts, ExecutingOptions _exec);
    void updateInfos(RunnableContextEl _ctx, Struct _infos, Struct _doneTests, Struct _method, Struct _count);
    void finish(RunnableContextEl _ctx, Struct _infos);
    void setResults(RunnableContextEl _ctx, Argument _res);
}
