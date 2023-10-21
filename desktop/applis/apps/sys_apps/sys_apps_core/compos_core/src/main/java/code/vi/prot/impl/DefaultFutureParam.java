package code.vi.prot.impl;

import code.threads.AbstractFutureParam;

import java.util.concurrent.Future;

public final class DefaultFutureParam<T> implements AbstractFutureParam<T> {
    private final Future<T> future;
    public DefaultFutureParam(Future<T> _future){
        future = _future;
    }
    @Override
    public boolean cancel(boolean _mayInterruptIfRunning) {
        return future.cancel(_mayInterruptIfRunning);
    }

    @Override
    public T attendreResultat() {
        try {
            return future.get();
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            return null;
        }
    }

    @Override
    public boolean attendre() {
        try {
            future.get();
            return true;
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }
}
