package code.threads;

public interface Locking {
    Thread getCurrentThread();
    boolean isCurrentThreadEnded();
}
