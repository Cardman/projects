package code.mock;

import code.threads.*;

public final class MockBaseExecutorServiceParam<T> extends MockBaseExecutorService implements AbstractBaseExecutorServiceParam<T> {

    @Override
    public AbstractBaseExecutorServiceParam<T> copy() {
        return new MockBaseExecutorServiceParam<T>();
    }

    @Override
    public AbstractFutureParam<T> submitCallable(IntCallable<T> _run) {
        T r_;
        if (!isCancel() && _run != null) {
            r_ = _run.call();
        } else {
            r_ = null;
        }
        return new MockFutureParam<T>(r_, isCancel());
    }

    @Override
    public AbstractFutureParam<T> submitWrCallable(IntCallable<T> _run) {
        return new MockFutureCallableParam<T>(_run);
    }

    @Override
    public AbstractFutureParam<T> submitLater(IntCallable<T> _command) {
        return new MockLaterFutureParam<T>(_command, isCancel());
    }
}
