package code.expressionlanguage.utilcompo;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.WithoutParentIdStruct;
import code.expressionlanguage.utilimpl.LgNamesUtils;
import code.threads.AbstractFuture;

public final class FutureStruct extends WithoutParentIdStruct {
    private final AbstractFuture future;

    public FutureStruct(AbstractFuture _f) {
        this.future = _f;
    }

    public void attendre() {
        future.attendre();
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return ((LgNamesUtils)_contextEl.getStandards()).getCustAliases().getAliasFuture();
    }
}
