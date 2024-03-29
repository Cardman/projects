package code.mock;

import code.threads.*;
import code.util.*;

public final class MockScheduledExecutorService implements AbstractScheduledExecutorService {
    private boolean cancel;
    @Override
    public AbstractFuture scheduleAtFixedRate(Runnable _command, long _initialDelay, long _period) {
        return new MockFuture(cancel, new IntMap<AbstractFuture>(), 0);
    }

    @Override
    public AbstractFuture scheduleAtFixedRateNanos(Runnable _command, long _initialDelay, long _period) {
        return new MockFuture(cancel, new IntMap<AbstractFuture>(), 0);
    }

    @Override
    public void shutdown() {
        cancel = true;
    }
}
