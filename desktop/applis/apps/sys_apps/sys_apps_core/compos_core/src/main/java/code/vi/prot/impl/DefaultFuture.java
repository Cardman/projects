package code.vi.prot.impl;

import code.threads.AbstractFuture;

import java.util.concurrent.Future;

public final class DefaultFuture implements AbstractFuture {
    private final Future<?> future;
    public DefaultFuture(Future<?> _future){
        future = _future;
    }
    @Override
    public boolean cancel(boolean _mayInterruptIfRunning) {
        return future.cancel(_mayInterruptIfRunning);
    }
}
