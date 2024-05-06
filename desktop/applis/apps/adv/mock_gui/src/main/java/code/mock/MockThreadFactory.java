package code.mock;

import code.threads.*;
import code.util.CustList;

public final class MockThreadFactory implements AbstractThreadFactory {
    private final MockAbsRand mockRand;
    private final AbstractDateFactory mockDateFactory = new MockDateFactory();
    private final AbstractScheduledExecutorService scheduledExecutorService = new MockScheduledExecutorService();
    private final AbstractAtomicLong ids = new ConcreteLong();
    private final MockFileSet fileSet;
    private final CustList<AbstractThread> allThreads = new CustList<AbstractThread>();

    public MockThreadFactory(MockAbsRand _gen, MockFileSet _mfs) {
        fileSet = _mfs;
        mockRand = _gen;
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
    public AbstractThread newStartedThread(Runnable _run) {
        MockThread th_ = new MockThread(_run, false, ids);
        th_.start();
        getAllThreads().add(th_);
        return th_;
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
        return newExecutorService(1);
    }

    @Override
    public AbstractBaseExecutorService newExecutorService(int _nbThreads) {
        return new MockBaseExecutorService();
    }

    @Override
    public AbstractAtomicBoolean newAtomicBoolean() {
        return new ConcreteBoolean();
    }

    @Override
    public AbstractAtomicBoolean newAtomicBoolean(boolean _value) {
        return new ConcreteBoolean(_value);
    }

    @Override
    public AbstractAtomicInteger newAtomicInteger() {
        return new ConcreteInteger();
    }

    @Override
    public AbstractAtomicInteger newAtomicInteger(int _value) {
        return new ConcreteInteger(_value);
    }

    @Override
    public AbstractAtomicLong newAtomicLong() {
        return new ConcreteLong();
    }

    @Override
    public AbstractAtomicLong newAtomicLong(long _value) {
        return new ConcreteLong(_value);
    }

    public CustList<AbstractThread> getAllThreads() {
        return allThreads;
    }
}
