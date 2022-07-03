package code.mock;

import code.threads.AbstractAtomicLong;

public final class MockAtomicLong implements AbstractAtomicLong {
    private long lgValue;
    public MockAtomicLong() {
        this(0);
    }

    public MockAtomicLong(long _i) {
        lgValue = _i;
    }

    @Override
    public long get() {
        return lgValue;
    }

    @Override
    public void set(long _value) {
        lgValue = _value;
    }

    @Override
    public boolean compareAndSet(long _one, long _two) {
        if (lgValue == _one) {
            lgValue = _two;
            return true;
        }
        return false;
    }

    @Override
    public long getAndSet(long _value) {
        long old_ = lgValue;
        lgValue = _value;
        return old_;
    }

    @Override
    public long getAndAdd(long _value) {
        long old_ = lgValue;
        lgValue +=_value;
        return old_;
    }

    @Override
    public long addAndGet(long _value) {
        lgValue +=_value;
        return lgValue;
    }

    @Override
    public long getAndIncrement() {
        long old_ = lgValue;
        lgValue++;
        return old_;
    }

    @Override
    public long incrementAndGet() {
        lgValue++;
        return lgValue;
    }

    @Override
    public long getAndDecrement() {
        long old_ = lgValue;
        lgValue--;
        return old_;
    }

    @Override
    public long decrementAndGet() {
        lgValue--;
        return lgValue;
    }

    @Override
    public void lazySet(long _value) {
        lgValue = _value;
    }
}
