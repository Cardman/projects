package code.threads;

public interface AbstractThread {
    void start();
    int getPriority();
    void setPriority(int _prio);
    ThState join();
    boolean isAlive();
    long getId();
    Runnable getThread();
    boolean isInterrupted();
    void setInterrupted(boolean _interrupted);
}
