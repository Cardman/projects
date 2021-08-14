package code.threads;

public interface AbstractThreadFactory {
    AbstractDateFactory getDateFactory();
    long nanos();
    long millis();
    boolean sleep(long _time);
    void newStartedThread(Runnable _runnable);
    AbstractThread newThread(Runnable _runnable);
    AbstractThread newThread();
    AbstractScheduledExecutorService newScheduledExecutorService();
    AbstractAtomicBoolean newAtomicBoolean();
    AbstractAtomicBoolean newAtomicBoolean(boolean _value);
    AbstractAtomicInteger newAtomicInteger();
    AbstractAtomicInteger newAtomicInteger(int _value);
    AbstractAtomicLong newAtomicLong();
    AbstractAtomicLong newAtomicLong(long _value);
}
