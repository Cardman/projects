package code.vi.prot.impl;

import code.threads.AbstractBaseExecutorServiceParam;
import code.threads.AbstractFutureParam;
import code.threads.IntCallable;
import code.util.IntWrapCallable;

import java.util.concurrent.Callable;

public final class DefaultExecutorServiceParam<T> extends DefaultExecutorService implements AbstractBaseExecutorServiceParam<T> {

    public DefaultExecutorServiceParam() {
    }

    public DefaultExecutorServiceParam(int _nbThreads) {
        super(_nbThreads);
    }
    @Override
    public AbstractFutureParam<T> submitLater(IntWrapCallable<T> _command) {
        return submitCallable(_command);
    }

    @Override
    public AbstractFutureParam<T> submitWrCallable(IntCallable<T> _command) {
        return new DefaultFutureParam<T>(getTimer().submit(new DefCallable<T>(_command)));
    }

    @Override
    public AbstractFutureParam<T> submitCallable(IntWrapCallable<T> _command) {
        return new DefaultFutureParam<T>(getTimer().submit((Callable<T>) _command));
    }
}
