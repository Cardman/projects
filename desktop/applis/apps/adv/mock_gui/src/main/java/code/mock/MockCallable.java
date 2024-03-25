package code.mock;

import code.threads.IntCallable;

public final class MockCallable<T> implements IntCallable<T> {
    private boolean started;
    private final T info;
    public MockCallable(T _t) {
        info = _t;
    }

    @Override
    public T call() {
        started = true;
        return info;
    }

    public boolean isStarted() {
        return started;
    }
}
