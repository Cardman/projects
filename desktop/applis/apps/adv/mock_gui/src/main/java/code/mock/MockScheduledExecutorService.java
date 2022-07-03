package code.mock;

import code.threads.AbstractFuture;
import code.threads.AbstractScheduledExecutorService;

public final class MockScheduledExecutorService implements AbstractScheduledExecutorService {
    private boolean cancel;
    @Override
    public AbstractFuture scheduleAtFixedRate(Runnable _command, long _initialDelay, long _period) {
        return new MockFuture(cancel);
    }

    @Override
    public AbstractFuture scheduleAtFixedRateNanos(Runnable _command, long _initialDelay, long _period) {
        return new MockFuture(cancel);
    }

    @Override
    public void shutdown() {
        cancel = true;
    }
}
