package code.threads;

public interface AbstractThreadFactory extends AbstractTimerRetriever {
    AbstractConcurrentMap<String, FileStruct> newMapStringFileStruct();
    AbstractDateFactory getDateFactory();
//    void yieldThread();
    int sleep(long _time);
    AbstractThread newStartedThread(Runnable _runnable);
    void newStartedThread(Runnable _runnable, boolean _immediate);
    AbstractThread newThread(Runnable _runnable);
    AbstractThread newThread(Runnable _runnable, boolean _immediate);
    AbstractThread newThread();
    AbstractScheduledExecutorService newScheduledExecutorService();
    AbstractBaseExecutorService newExecutorService();
    AbstractBaseExecutorService newExecutorService(int _nbThreads);
    AbstractAtomicBoolean newAtomicBoolean();
    AbstractAtomicBoolean newAtomicBoolean(boolean _value);
    AbstractAtomicInteger newAtomicInteger();
    AbstractAtomicInteger newAtomicInteger(int _value);
    AbstractAtomicLong newAtomicLong();
    AbstractAtomicLong newAtomicLong(long _value);
}
