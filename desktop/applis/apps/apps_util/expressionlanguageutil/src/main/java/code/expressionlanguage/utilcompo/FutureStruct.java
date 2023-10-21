package code.expressionlanguage.utilcompo;

import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.threads.AbstractFuture;

public final class FutureStruct extends AbsFutureStruct {
    private final AbstractFuture future;

    public FutureStruct(AbstractFuture _f) {
        this.future = _f;
    }

    public Struct attendre() {
        future.attendre();
        return NullStruct.NULL_VALUE;
    }

    @Override
    public AbstractFuture getFuture() {
        return future;
    }
}
