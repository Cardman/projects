package code.threads;

public interface AbstractThreadFactory {
    AbstractConcurrentMap<String, FileStruct> newMapStringFileStruct();
    AbstractDateFactory getDateFactory();
    long nanos();
    long millis();
//    void yieldThread();
    boolean sleep(long _time);
    void newStartedThread(Runnable _runnable);
    AbstractThread newThread(Runnable _runnable);
    AbstractThread newThread(Runnable _runnable, boolean _immediate);
    AbstractThread newThread();
    AbstractScheduledExecutorService newScheduledExecutorService();
    AbstractBaseExecutorService newExecutorService();
    AbstractAtomicBoolean newAtomicBoolean();
    AbstractAtomicBoolean newAtomicBoolean(boolean _value);
    AbstractAtomicInteger newAtomicInteger();
    AbstractAtomicInteger newAtomicInteger(int _value);
    AbstractAtomicLong newAtomicLong();
    AbstractAtomicLong newAtomicLong(long _value);
}
