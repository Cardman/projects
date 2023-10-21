package code.vi.prot.impl;

import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.StructCallable;
import code.util.IntWrapCallable;

import java.util.concurrent.Callable;

public final class DefCallableStruct implements IntWrapCallable<Struct>, Callable<Struct> {
    private final StructCallable callable;

    public DefCallableStruct(StructCallable _c) {
        this.callable = _c;
    }
    @Override
    public Struct wrap() {
        return callable.call();
    }

    @Override
    public Struct call() {
        return wrap();
    }
}
