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
        return new MockFutureParam<T>(r_, isCancel(), getTasks(), 0);
    }

    @Override
    public AbstractFutureParam<T> submitWrCallable(IntCallable<T> _run) {
        int i_ = incr();
        MockFutureCallableParam<T> m_ = new MockFutureCallableParam<T>(_run, getTasks(), i_);
        getTasks().addEntry(i_,m_);
        return m_;
    }

    @Override
    public AbstractFutureParam<T> submitLater(IntCallable<T> _command) {
        return new MockLaterFutureParam<T>(_command, isCancel());
    }
}
