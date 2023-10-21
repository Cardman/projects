package code.mock;

import code.threads.AbstractFutureParam;

public final class MockFutureParam<T> extends MockFuture implements AbstractFutureParam<T> {
    private final T def;
    public MockFutureParam(T _c, boolean _s) {
        super(_s);
        def = _c;
    }
    @Override
    public T attendreResultat() {
        return def;
    }
}
