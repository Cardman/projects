package code.threads;

public interface Locking {
    AbstractThreadFactory getCurrentThreadFactory();
    AbstractThread getCurrentThread();
    boolean isCurrentThreadEnded();
}
