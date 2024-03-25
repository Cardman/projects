package code.mock;

import code.threads.AbstractFutureParam;
import code.threads.IntCallable;

public final class MockLaterFutureParam<T> implements AbstractFutureParam<T> {
    private final boolean shutdown;
    private final IntCallable<T> task;

    public MockLaterFutureParam(IntCallable<T> _r, boolean _s) {
        task = _r;
        shutdown = _s;
    }
    @Override
    public boolean cancel(boolean _b) {
        return shutdown;
    }

    @Override
    public T attendreResultat() {
        if (!shutdown) {
            return task.call();
        }
        return null;
    }

    @Override
    public boolean attendre() {
        if (!shutdown) {
            task.call();
        }
        return shutdown;
    }
}
