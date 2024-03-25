package code.vi.prot.impl;

import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.StructCallable;

import java.util.concurrent.Callable;

public final class DefCallableStruct implements Callable<Struct> {
    private final StructCallable callable;

    public DefCallableStruct(StructCallable _c) {
        this.callable = _c;
    }

    @Override
    public Struct call() {
        return callable.call();
    }
}
