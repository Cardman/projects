package code.vi.prot.impl;

import code.threads.*;
import code.util.IntWrapCallable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class DefaultExecutorServiceParam<T> implements AbstractBaseExecutorServiceParam<T> {
    private final ExecutorService timer;

    public DefaultExecutorServiceParam() {
        timer = Executors.newSingleThreadExecutor();
    }

    public DefaultExecutorServiceParam(int _nbThreads) {
        timer = Executors.newFixedThreadPool(_nbThreads);
    }

    @Override
    public void shutdown() {
        timer.shutdown();
    }

    @Override
    public AbstractFuture submitLater(Runnable _command) {
        return submit(_command);
    }
    @Override
    public AbstractFutureParam<T> submitLater(IntWrapCallable<T> _command) {
        return submitCallable(_command);
    }

    @Override
    public AbstractFuture submit(Runnable _command) {
        return new DefaultFuture(timer.submit(_command));
    }

    @Override
    public AbstractFutureParam<T> submitCallable(IntWrapCallable<T> _command) {
        return new DefaultFutureParam<T>(timer.submit((Callable<T>) _command));
    }
    @Override
    public void execute(Runnable _command) {
        timer.execute(_command);
    }
}
