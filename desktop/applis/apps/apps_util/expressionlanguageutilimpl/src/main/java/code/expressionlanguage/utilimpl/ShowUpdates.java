package code.expressionlanguage.utilimpl;

import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.LgNamesWithNewAliases;
import code.expressionlanguage.utilcompo.ProgressingTests;
import code.expressionlanguage.utilcompo.RunnableContextEl;

public final class ShowUpdates implements Runnable {
    private final Struct info;
    private final RunnableContextEl ctx;
    private final ProgressingTests progressingTests;
    private final LgNamesWithNewAliases evolved;
    public ShowUpdates(Struct _info, RunnableContextEl _ctx, ProgressingTests _progressingTests,LgNamesWithNewAliases _evolved) {
        info = _info;
        ctx = _ctx;
        progressingTests = _progressingTests;
        evolved = _evolved;
    }
    @Override
    public void run() {
        progressingTests.updateInfos(ctx,info, evolved);
    }
}
