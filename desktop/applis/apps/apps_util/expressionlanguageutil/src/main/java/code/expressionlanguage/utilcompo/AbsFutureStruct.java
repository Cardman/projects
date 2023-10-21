package code.expressionlanguage.utilcompo;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.structs.WithoutParentIdStruct;
import code.expressionlanguage.utilimpl.LgNamesUtils;
import code.threads.AbstractFuture;

public abstract class AbsFutureStruct extends WithoutParentIdStruct {

    public abstract AbstractFuture getFuture();

    public abstract Struct attendre();

    public boolean cancel() {
        return getFuture().cancel(false);
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return ((LgNamesUtils)_contextEl.getStandards()).getExecContent().getCustAliases().getAliasFuture();
    }
}
