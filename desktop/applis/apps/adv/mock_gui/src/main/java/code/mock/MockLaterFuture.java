package code.mock;

import code.threads.AbstractFuture;

public final class MockLaterFuture implements AbstractFuture {
    private final boolean shutdown;
    private final Runnable task;

    public MockLaterFuture(Runnable _r, boolean _s) {
        task = _r;
        shutdown = _s;
    }
    @Override
    public boolean cancel(boolean _b) {
        return shutdown;
    }

    @Override
    public boolean attendre() {
        if (!shutdown) {
            task.run();
        }
        return shutdown;
    }
}
