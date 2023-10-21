package code.expressionlanguage.utilcompo;

import code.expressionlanguage.structs.Struct;
import code.threads.AbstractFutureParam;

public final class FutureObjStruct extends AbsFutureStruct {
    private final AbstractFutureParam<Struct> future;

    public FutureObjStruct(AbstractFutureParam<Struct> _f) {
        this.future = _f;
    }

    public Struct attendre() {
        return future.attendreResultat();
    }

    @Override
    public AbstractFutureParam<Struct> getFuture() {
        return future;
    }

}
