package code.vi.prot.impl;

import code.threads.AbstractBaseExecutorServiceParam;
import code.threads.AbstractFutureParam;
import code.threads.IntCallable;

public final class DefaultExecutorServiceParam<T> extends DefaultExecutorService implements AbstractBaseExecutorServiceParam<T> {

    public DefaultExecutorServiceParam() {
    }

    public DefaultExecutorServiceParam(int _nbThreads) {
        super(_nbThreads);
    }
    @Override
    public AbstractBaseExecutorServiceParam<T> copy() {
        return new DefaultExecutorServiceParam<T>();
    }

    @Override
    public AbstractFutureParam<T> submitLater(IntCallable<T> _command) {
        return submitCallable(_command);
    }

    @Override
    public AbstractFutureParam<T> submitWrCallable(IntCallable<T> _command) {
        return new DefaultFutureParam<T>(getTimer().submit(new DefCallable<T>(_command)));
    }

    @Override
    public AbstractFutureParam<T> submitCallable(IntCallable<T> _command) {
        return new DefaultFutureParam<T>(getTimer().submit(new DefCallable<T>(_command)));
    }
}
