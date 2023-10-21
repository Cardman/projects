package code.mock;

import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.StructCallable;
import code.util.IntWrapCallable;

public final class MockWrapCallable implements IntWrapCallable<Struct> {
    private final StructCallable callable;

    public MockWrapCallable(StructCallable _c) {
        this.callable = _c;
    }

    @Override
    public Struct wrap() {
        return callable.call();
    }
}
