package code.mock;

import code.threads.AbstractFutureParam;
import code.util.IntWrapCallable;

public final class MockLaterFutureParam<T> implements AbstractFutureParam<T> {
    private final boolean shutdown;
    private final IntWrapCallable<T> task;

    public MockLaterFutureParam(IntWrapCallable<T> _r, boolean _s) {
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
            return task.wrap();
        }
        return null;
    }

    @Override
    public boolean attendre() {
        if (!shutdown) {
            task.wrap();
        }
        return shutdown;
    }
}
