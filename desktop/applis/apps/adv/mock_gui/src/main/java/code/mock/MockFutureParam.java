package code.mock;

import code.threads.*;
import code.util.*;

public final class MockFutureParam<T> extends MockFuture implements AbstractFutureParam<T> {
    private final T def;
    public MockFutureParam(T _c, boolean _s, IntMap<AbstractFuture> _t, int _id) {
        super(_s, _t, _id);
        def = _c;
    }
    @Override
    public T attendreResultat() {
        getTasks().removeKey(getIdTask());
        return def;
    }
}
