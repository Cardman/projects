package code.mock;

import code.threads.*;
import code.util.*;

public final class MockFutureCallableParam<T> extends MockFuture implements AbstractFutureParam<T> {
    private final IntCallable<T> running;
    private T def;
    private boolean done;
    public MockFutureCallableParam(IntCallable<T> _r, IntMap<AbstractFuture> _t, int _id) {
        super(false, _t, _id);
        running = _r;
    }
    @Override
    public T attendreResultat() {
        if (done) {
            return def;
        }
        def = running.call();
        done = true;
        getTasks().removeKey(getIdTask());
        return def;
    }
}
