package code.threads;

public interface AbstractThread {
    void start();
    int getPriority();
    boolean setPriority(int _prio);
    ThState join();
    boolean isAlive();
    long getId();
    Runnable getThread();
    Runnable getRunnable();
    boolean isInterrupted();
    void setInterrupted(boolean _interrupted);
}
