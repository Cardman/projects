package code.vi.sys.impl;

import code.threads.AbstractAtomicLong;

import java.util.concurrent.atomic.AtomicLong;

public final class DefAtomicLong implements AbstractAtomicLong {
    private final AtomicLong longValue;
    public DefAtomicLong() {
        longValue = new AtomicLong();
    }
    public DefAtomicLong(long _value) {
        longValue = new AtomicLong(_value);
    }

    @Override
    public long get() {
        return longValue.get();
    }

    @Override
    public void set(long _value) {
        longValue.set(_value);
    }

    @Override
    public boolean compareAndSet(long _one, long _two) {
        return longValue.compareAndSet(_one, _two);
    }

    @Override
    public long getAndSet(long _value) {
        return longValue.getAndSet(_value);
    }

    @Override
    public long getAndAdd(long _value) {
        return longValue.getAndAdd(_value);
    }

    @Override
    public long addAndGet(long _value) {
        return longValue.addAndGet(_value);
    }

    @Override
    public long getAndIncrement() {
        return longValue.getAndIncrement();
    }

    @Override
    public long incrementAndGet() {
        return longValue.incrementAndGet();
    }

    @Override
    public long getAndDecrement() {
        return longValue.getAndDecrement();
    }

    @Override
    public long decrementAndGet() {
        return longValue.decrementAndGet();
    }

    @Override
    public void lazySet(long _value) {
        longValue.lazySet(_value);
    }
}
