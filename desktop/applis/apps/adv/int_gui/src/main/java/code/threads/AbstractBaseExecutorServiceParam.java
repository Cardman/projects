package code.threads;

public interface AbstractBaseExecutorServiceParam<T> extends AbstractBaseExecutorService {
    AbstractFutureParam<T> submitCallable(IntCallable<T> _command);
    AbstractFutureParam<T> submitWrCallable(IntCallable<T> _command);
    AbstractFutureParam<T> submitLater(IntCallable<T> _command);
    AbstractBaseExecutorServiceParam<T> copy();
}
