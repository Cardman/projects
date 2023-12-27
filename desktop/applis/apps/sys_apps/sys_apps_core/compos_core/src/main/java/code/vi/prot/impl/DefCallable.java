package code.vi.prot.impl;

import code.threads.IntCallable;

import java.util.concurrent.Callable;

public final class DefCallable<T> implements Callable<T> {
    private final IntCallable<T> callable;

    public DefCallable(IntCallable<T> _c) {
        this.callable = _c;
    }

    @Override
    public T call() {
        return callable.call();
    }
}
