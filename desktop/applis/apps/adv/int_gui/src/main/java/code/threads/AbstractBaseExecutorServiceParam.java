package code.threads;

import code.util.IntWrapCallable;

public interface AbstractBaseExecutorServiceParam<T> extends AbstractBaseExecutorService {
    AbstractFutureParam<T> submitCallable(IntWrapCallable<T> _command);
    AbstractFutureParam<T> submitWrCallable(IntCallable<T> _command);
    AbstractFutureParam<T> submitLater(IntWrapCallable<T> _command);
}
