package code.threads;

public interface AbstractBaseExecutorService extends AbstractShutdownExecutorService {
    void execute(Runnable _command);
    AbstractFuture submit(Runnable _command);
}
