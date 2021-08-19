package code.sys.impl;

import code.threads.*;

public final class DefaultThreadFactory implements AbstractThreadFactory {

    private final AbstractDateFactory dateFactory = new DefaultDateFactory();

    @Override
    public AbstractConcurrentMap<String, FileStruct> newMapStringFileStruct() {
        return new DefConcurrentMap<String, FileStruct>();
    }

    @Override
    public AbstractDateFactory getDateFactory() {
        return dateFactory;
    }

    @Override
    public long nanos() {
        return System.nanoTime();
    }

    @Override
    public long millis() {
        return System.currentTimeMillis();
    }

    @Override
    public boolean sleep(long _time) {
        return DefaultThread.simpleSleep(_time);
    }

    @Override
    public void newStartedThread(Runnable _runnable) {
        newThread(_runnable).start();
    }

    @Override
    public AbstractThread newThread(Runnable _runnable) {
        return new DefaultThread(_runnable);
    }

    @Override
    public AbstractThread newThread() {
        return new DefaultThread();
    }

    @Override
    public AbstractScheduledExecutorService newScheduledExecutorService() {
        return new DefaultScheduledExecutorService();
    }

    @Override
    public AbstractAtomicBoolean newAtomicBoolean() {
        return new DefAtomicBoolean();
    }

    @Override
    public AbstractAtomicBoolean newAtomicBoolean(boolean _value) {
        return new DefAtomicBoolean(_value);
    }

    @Override
    public AbstractAtomicInteger newAtomicInteger() {
        return new DefAtomicInteger();
    }

    @Override
    public AbstractAtomicInteger newAtomicInteger(int _value) {
        return new DefAtomicInteger(_value);
    }

    @Override
    public AbstractAtomicLong newAtomicLong() {
        return new DefAtomicLong();
    }

    @Override
    public AbstractAtomicLong newAtomicLong(long _value) {
        return new DefAtomicLong(_value);
    }
}
