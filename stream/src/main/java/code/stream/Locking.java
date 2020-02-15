package code.stream;

public interface Locking {
    Thread getCurrentThread();
    boolean isCurrentThreadEnded();
}
