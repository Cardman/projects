package code.gui;

import code.threads.*;

public class ThreadFactorySample implements AbstractThreadFactory {
    @Override
    public AbstractConcurrentMap<String, FileStruct> newMapStringFileStruct() {
        return null;
    }

    @Override
    public AbstractDateFactory getDateFactory() {
        return null;
    }

    @Override
    public long nanos() {
        return 0;
    }

    @Override
    public long millis() {
        return 0;
    }

    @Override
    public boolean sleep(long _time) {
        return false;
    }

    @Override
    public void newStartedThread(Runnable _runnable) {

    }

    @Override
    public void newStartedThread(Runnable _runnable, boolean _immediate) {

    }

    @Override
    public AbstractThread newThread(Runnable _runnable) {
        return null;
    }

    @Override
    public AbstractThread newThread(Runnable _runnable, boolean _immediate) {
        return null;
    }

    @Override
    public AbstractThread newThread() {
        return null;
    }

    @Override
    public AbstractScheduledExecutorService newScheduledExecutorService() {
        return null;
    }

    @Override
    public AbstractBaseExecutorService newExecutorService() {
        return null;
    }

    @Override
    public AbstractAtomicBoolean newAtomicBoolean() {
        return null;
    }

    @Override
    public AbstractAtomicBoolean newAtomicBoolean(boolean _value) {
        return null;
    }

    @Override
    public AbstractAtomicInteger newAtomicInteger() {
        return null;
    }

    @Override
    public AbstractAtomicInteger newAtomicInteger(int _value) {
        return null;
    }

    @Override
    public AbstractAtomicLong newAtomicLong() {
        return null;
    }

    @Override
    public AbstractAtomicLong newAtomicLong(long _value) {
        return null;
    }
}
