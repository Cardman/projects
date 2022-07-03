package code.mock;

import code.maths.montecarlo.AbstractGenerator;
import code.threads.*;

public final class MockThreadFactory implements AbstractThreadFactory {
    private final MockRand mockRand;
    private final AbstractDateFactory mockDateFactory = new MockDateFactory();
    private final AbstractScheduledExecutorService scheduledExecutorService = new MockScheduledExecutorService();
    private final AbstractBaseExecutorService baseExecutorService = new MockBaseExecutorService();
    private final AbstractAtomicLong ids = new MockAtomicLong();
    private final MockFileSet fileSet;

    public MockThreadFactory(AbstractGenerator _gen, MockFileSet _mfs) {
        fileSet = _mfs;
        mockRand = new MockRand(_gen);
    }
    @Override
    public AbstractConcurrentMap<String, FileStruct> newMapStringFileStruct() {
        return new MockConcurrentMapStringFileStruct();
    }

    @Override
    public AbstractDateFactory getDateFactory() {
        return mockDateFactory;
    }

    @Override
    public long nanos() {
        return 0;
    }

    @Override
    public long millis() {
        return fileSet.getMockMillis().millis();
    }

    @Override
    public boolean sleep(long _time) {
        return mockRand.edit();
    }

    @Override
    public void newStartedThread(Runnable _run) {
        new MockThread(_run, false,ids).start();
    }

    @Override
    public void newStartedThread(Runnable _runnable, boolean _immediate) {
        new MockThread(_runnable, _immediate,ids).start();
    }

    @Override
    public AbstractThread newThread(Runnable _run) {
        return new MockThread(_run, false,ids);
    }

    @Override
    public AbstractThread newThread(Runnable _run, boolean _immediate) {
        return new MockThread(_run, _immediate,ids);
    }

    @Override
    public AbstractThread newThread() {
        return new MockThread(null, false,ids);
    }

    @Override
    public AbstractScheduledExecutorService newScheduledExecutorService() {
        return scheduledExecutorService;
    }

    @Override
    public AbstractBaseExecutorService newExecutorService() {
        return baseExecutorService;
    }

    @Override
    public AbstractAtomicBoolean newAtomicBoolean() {
        return new MockAtomicBoolean();
    }

    @Override
    public AbstractAtomicBoolean newAtomicBoolean(boolean _value) {
        return new MockAtomicBoolean(_value);
    }

    @Override
    public AbstractAtomicInteger newAtomicInteger() {
        return new MockAtomicInteger();
    }

    @Override
    public AbstractAtomicInteger newAtomicInteger(int _value) {
        return new MockAtomicInteger(_value);
    }

    @Override
    public AbstractAtomicLong newAtomicLong() {
        return new MockAtomicLong();
    }

    @Override
    public AbstractAtomicLong newAtomicLong(long _value) {
        return new MockAtomicLong(_value);
    }
}
