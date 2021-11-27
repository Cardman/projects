package code.vi.prot.impl;

import code.threads.AbstractFuture;
import code.threads.AbstractScheduledExecutorService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class DefaultScheduledExecutorService implements AbstractScheduledExecutorService {
    private final ScheduledExecutorService timer = Executors.newSingleThreadScheduledExecutor();
    @Override
    public AbstractFuture scheduleAtFixedRate(Runnable _command, long _initialDelay, long _period) {
        return new DefaultFuture(timer.scheduleAtFixedRate(_command,_initialDelay,_period, TimeUnit.MILLISECONDS));
    }
}
