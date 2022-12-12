package code.threads;

public interface AbstractBaseExecutorService {
    void execute(Runnable _command);
    AbstractFuture submit(Runnable _command);
    void shutdown();
}
