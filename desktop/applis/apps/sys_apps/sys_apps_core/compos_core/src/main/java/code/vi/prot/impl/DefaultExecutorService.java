package code.vi.prot.impl;

import code.threads.AbstractBaseExecutorService;
import code.threads.AbstractFuture;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class DefaultExecutorService implements AbstractBaseExecutorService {
    private final ExecutorService timer;

    public DefaultExecutorService() {
        timer = Executors.newSingleThreadExecutor();
    }

    public DefaultExecutorService(int _nbThreads) {
        timer = Executors.newFixedThreadPool(_nbThreads);
    }
    @Override
    public void execute(Runnable _command) {
        timer.execute(_command);
    }

    @Override
    public void shutdown() {
        timer.shutdown();
    }

    @Override
    public AbstractFuture submit(Runnable _command) {
        return new DefaultFuture(timer.submit(_command));
    }
}
