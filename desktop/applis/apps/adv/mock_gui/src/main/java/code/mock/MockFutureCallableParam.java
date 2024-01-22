package code.mock;

import code.threads.AbstractFutureParam;
import code.threads.IntCallable;

public final class MockFutureCallableParam<T> extends MockFuture implements AbstractFutureParam<T> {
    private final IntCallable<T> running;
    private T def;
    private boolean done;
    public MockFutureCallableParam(IntCallable<T> _r) {
        super(false);
        running = _r;
    }
    @Override
    public T attendreResultat() {
        if (done) {
            return def;
        }
        def = running.call();
        done = true;
        return def;
    }
}
