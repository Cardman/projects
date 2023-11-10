package code.expressionlanguage.utilimpl;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.LgNamesWithNewAliases;
import code.expressionlanguage.utilcompo.ProgressingTests;

public final class ShowUpdates implements Runnable {
    private final Struct info;
    private final ContextEl ctx;
    private final ProgressingTests progressingTests;
    private final LgNamesWithNewAliases evolved;
    public ShowUpdates(Struct _info, ContextEl _ctx, ProgressingTests _progressingTests,LgNamesWithNewAliases _evolved) {
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
